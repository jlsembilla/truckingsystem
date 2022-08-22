package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.grpc.Context;

public class Employee_Scan_Front_ID extends AppCompatActivity {

    //region Variable Declaration (ImageView, Buttons)
    ImageView idFrontView;
    Button capture, selectFile, proceedUpload;
    //endregion
    //region Variable Declaration (Firebase Storage)
    EmployeeID getLoginDetails = new EmployeeID();
    StorageReference firebaseStorage;
    //endregion
    //region Variable Declaration (Permissions, Distinction)
    private static final int REQUEST_CODE_PERMISSIONS = 1;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 2;
    private static final int REQUEST_CODE_STORAGE_PERMISSIONS = 3;
    private static final int REQUEST_CODE_SELECT_IMAGE = 4;
    private static int METHOD_OF_UPLOAD;
    //endregion
    //region Variable Declaration (Uploading Picture)
    private String currentImagePath;
    private Uri cameraContentUri;
    private Uri galleryContentUri;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_scan_front_id);

        //region Variable Initialization
        capture = findViewById(R.id.captureFrontView);
        selectFile = findViewById(R.id.selectImage);
        proceedUpload = findViewById(R.id.uploadFrontID);
        idFrontView = findViewById(R.id.frontViewImage);
        firebaseStorage = FirebaseStorage.getInstance().getReference();
        //endregion
        //region Adding Event Listeners

        //Listener for Capture Camera Button
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Employee_Scan_Front_ID.this, "Capture button clicked", Toast.LENGTH_SHORT).show();
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Employee_Scan_Front_ID.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSIONS);

                }
                else {
                    dispatchCaptureImageIntent();
                }
            }
        });

        //Listener for Select File Button
        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Employee_Scan_Front_ID.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSIONS);
                }
                else {
                    selectImage();
                }
            }
        });

        //Listener for Uploading Image to Firebase
        proceedUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(METHOD_OF_UPLOAD == REQUEST_CODE_CAPTURE_IMAGE){
                    uploadImageToFirebase(getLoginDetails.getEmployeeName() + "_FRONT_"  + getLoginDetails.getEmployeeID(), cameraContentUri);
                }
                else if(METHOD_OF_UPLOAD == REQUEST_CODE_SELECT_IMAGE){
                    uploadImageToFirebase(getLoginDetails.getEmployeeName() + "_FRONT_" + getLoginDetails.getEmployeeID(), galleryContentUri);
                }
            }
        });
        //endregion
    }

    //region Methods on Using Camera For Image
    private void dispatchCaptureImageIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = createImageFile();
            } catch (IOException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if(imageFile != null){
                Uri imageUri = FileProvider.getUriForFile(this, "com.jobando.jobandotrucking.fileprovider", imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE);
            }
        }
    }

    private File createImageFile() throws IOException {

        String fileName = "IMAGE_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());

        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(fileName, ".jpg", directory);

        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    private Bitmap getscaledBitmap(ImageView imageView){

        BitmapFactory.Options options = new BitmapFactory.Options();
        int scaledFactor = Math.min(options.outWidth / imageView.getWidth(), options.outHeight / imageView.getHeight());

        options.inJustDecodeBounds = false;
        options.inSampleSize = scaledFactor;
        options.inPurgeable = true;

        return BitmapFactory.decodeFile(currentImagePath, options);
    }
    //endregion
    //region Methods on Using Gallery for Image
    private void selectImage(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    private String getPathFromUri(Uri contentUri) {
        String filePath;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);

        if(cursor == null) {
            filePath = contentUri.getPath();
        }
        else{
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
    //endregion
    //region Method for Uploading Image to Firebase
    private void uploadImageToFirebase(String file_Name, Uri contentUri) {

        StorageReference image  = firebaseStorage.child("DriverLicenses/" + getLoginDetails.getEmployeeID() + "/" + file_Name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("TAE", "Url: " + uri);
                    }
                });
                Toast.makeText(Employee_Scan_Front_ID.this, "Upload Success!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Employee_Scan_Front_ID.this, Employee_Scan_Back_ID.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Employee_Scan_Front_ID.this, "Upload Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //endregion
    //region Override Methods
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_PERMISSIONS && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                dispatchCaptureImageIntent();
            } else {
                Toast.makeText(this, "Not all permissions are granted", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == REQUEST_CODE_STORAGE_PERMISSIONS && grantResults.length > 0){
            if (grantResults[2] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }
            else {
                Toast.makeText(this, "Read Storage Permission not Granted", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK){

            File imageFile = new File(currentImagePath);
            idFrontView.setImageBitmap(getscaledBitmap(idFrontView));

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            cameraContentUri = Uri.fromFile(imageFile);
            mediaScanIntent.setData(cameraContentUri);
            this.sendBroadcast(mediaScanIntent);

            METHOD_OF_UPLOAD = requestCode;
        }
        else if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                galleryContentUri = data.getData();
                if(galleryContentUri != null){
                    try{
                        InputStream inputStream = getContentResolver().openInputStream(galleryContentUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        idFrontView.setImageBitmap(bitmap);

                        File selectedImageFile = new File(getPathFromUri(galleryContentUri));

                        METHOD_OF_UPLOAD = requestCode;
                    }
                    catch(Exception e){
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    //endregion
}