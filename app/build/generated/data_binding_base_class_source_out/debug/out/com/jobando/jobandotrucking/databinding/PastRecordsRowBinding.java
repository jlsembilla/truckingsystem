// Generated by view binder compiler. Do not edit!
package com.jobando.jobandotrucking.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.jobando.jobandotrucking.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PastRecordsRowBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView deliveryDate;

  @NonNull
  public final ImageView image;

  @NonNull
  public final TextView placeTitle;

  @NonNull
  public final TextView productDescription;

  private PastRecordsRowBinding(@NonNull CardView rootView, @NonNull TextView deliveryDate,
      @NonNull ImageView image, @NonNull TextView placeTitle,
      @NonNull TextView productDescription) {
    this.rootView = rootView;
    this.deliveryDate = deliveryDate;
    this.image = image;
    this.placeTitle = placeTitle;
    this.productDescription = productDescription;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static PastRecordsRowBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PastRecordsRowBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.past_records_row, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PastRecordsRowBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deliveryDate;
      TextView deliveryDate = ViewBindings.findChildViewById(rootView, id);
      if (deliveryDate == null) {
        break missingId;
      }

      id = R.id.image;
      ImageView image = ViewBindings.findChildViewById(rootView, id);
      if (image == null) {
        break missingId;
      }

      id = R.id.placeTitle;
      TextView placeTitle = ViewBindings.findChildViewById(rootView, id);
      if (placeTitle == null) {
        break missingId;
      }

      id = R.id.productDescription;
      TextView productDescription = ViewBindings.findChildViewById(rootView, id);
      if (productDescription == null) {
        break missingId;
      }

      return new PastRecordsRowBinding((CardView) rootView, deliveryDate, image, placeTitle,
          productDescription);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
