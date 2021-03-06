// Generated by view binder compiler. Do not edit!
package com.jobando.jobandotrucking.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jobando.jobandotrucking.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAdminDriverRegistrationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputEditText ageText;

  @NonNull
  public final TextInputEditText emailText;

  @NonNull
  public final TextInputEditText firstNameText;

  @NonNull
  public final TextInputEditText lastNameText;

  @NonNull
  public final TextInputEditText middleNameText;

  @NonNull
  public final TextInputEditText plateNumberText;

  @NonNull
  public final Button registerBtn;

  @NonNull
  public final TextInputLayout text1;

  @NonNull
  public final TextInputLayout text2;

  @NonNull
  public final TextInputLayout text3;

  @NonNull
  public final TextInputLayout text4;

  @NonNull
  public final TextInputLayout text5;

  @NonNull
  public final TextInputLayout text6;

  @NonNull
  public final TextInputLayout text7;

  @NonNull
  public final TextInputLayout text8;

  @NonNull
  public final TextInputEditText truckModelText;

  @NonNull
  public final TextInputEditText workExperienceText;

  private ActivityAdminDriverRegistrationBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputEditText ageText, @NonNull TextInputEditText emailText,
      @NonNull TextInputEditText firstNameText, @NonNull TextInputEditText lastNameText,
      @NonNull TextInputEditText middleNameText, @NonNull TextInputEditText plateNumberText,
      @NonNull Button registerBtn, @NonNull TextInputLayout text1, @NonNull TextInputLayout text2,
      @NonNull TextInputLayout text3, @NonNull TextInputLayout text4,
      @NonNull TextInputLayout text5, @NonNull TextInputLayout text6,
      @NonNull TextInputLayout text7, @NonNull TextInputLayout text8,
      @NonNull TextInputEditText truckModelText, @NonNull TextInputEditText workExperienceText) {
    this.rootView = rootView;
    this.ageText = ageText;
    this.emailText = emailText;
    this.firstNameText = firstNameText;
    this.lastNameText = lastNameText;
    this.middleNameText = middleNameText;
    this.plateNumberText = plateNumberText;
    this.registerBtn = registerBtn;
    this.text1 = text1;
    this.text2 = text2;
    this.text3 = text3;
    this.text4 = text4;
    this.text5 = text5;
    this.text6 = text6;
    this.text7 = text7;
    this.text8 = text8;
    this.truckModelText = truckModelText;
    this.workExperienceText = workExperienceText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAdminDriverRegistrationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAdminDriverRegistrationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_admin_driver_registration, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAdminDriverRegistrationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ageText;
      TextInputEditText ageText = ViewBindings.findChildViewById(rootView, id);
      if (ageText == null) {
        break missingId;
      }

      id = R.id.emailText;
      TextInputEditText emailText = ViewBindings.findChildViewById(rootView, id);
      if (emailText == null) {
        break missingId;
      }

      id = R.id.firstNameText;
      TextInputEditText firstNameText = ViewBindings.findChildViewById(rootView, id);
      if (firstNameText == null) {
        break missingId;
      }

      id = R.id.lastNameText;
      TextInputEditText lastNameText = ViewBindings.findChildViewById(rootView, id);
      if (lastNameText == null) {
        break missingId;
      }

      id = R.id.middleNameText;
      TextInputEditText middleNameText = ViewBindings.findChildViewById(rootView, id);
      if (middleNameText == null) {
        break missingId;
      }

      id = R.id.plateNumberText;
      TextInputEditText plateNumberText = ViewBindings.findChildViewById(rootView, id);
      if (plateNumberText == null) {
        break missingId;
      }

      id = R.id.registerBtn;
      Button registerBtn = ViewBindings.findChildViewById(rootView, id);
      if (registerBtn == null) {
        break missingId;
      }

      id = R.id.text1;
      TextInputLayout text1 = ViewBindings.findChildViewById(rootView, id);
      if (text1 == null) {
        break missingId;
      }

      id = com.karumi.dexter.R.id.text2;
      TextInputLayout text2 = ViewBindings.findChildViewById(rootView, id);
      if (text2 == null) {
        break missingId;
      }

      id = R.id.text3;
      TextInputLayout text3 = ViewBindings.findChildViewById(rootView, id);
      if (text3 == null) {
        break missingId;
      }

      id = R.id.text4;
      TextInputLayout text4 = ViewBindings.findChildViewById(rootView, id);
      if (text4 == null) {
        break missingId;
      }

      id = R.id.text5;
      TextInputLayout text5 = ViewBindings.findChildViewById(rootView, id);
      if (text5 == null) {
        break missingId;
      }

      id = R.id.text6;
      TextInputLayout text6 = ViewBindings.findChildViewById(rootView, id);
      if (text6 == null) {
        break missingId;
      }

      id = R.id.text7;
      TextInputLayout text7 = ViewBindings.findChildViewById(rootView, id);
      if (text7 == null) {
        break missingId;
      }

      id = R.id.text8;
      TextInputLayout text8 = ViewBindings.findChildViewById(rootView, id);
      if (text8 == null) {
        break missingId;
      }

      id = R.id.truckModelText;
      TextInputEditText truckModelText = ViewBindings.findChildViewById(rootView, id);
      if (truckModelText == null) {
        break missingId;
      }

      id = R.id.workExperienceText;
      TextInputEditText workExperienceText = ViewBindings.findChildViewById(rootView, id);
      if (workExperienceText == null) {
        break missingId;
      }

      return new ActivityAdminDriverRegistrationBinding((ConstraintLayout) rootView, ageText,
          emailText, firstNameText, lastNameText, middleNameText, plateNumberText, registerBtn,
          text1, text2, text3, text4, text5, text6, text7, text8, truckModelText,
          workExperienceText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
