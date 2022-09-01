// Generated by view binder compiler. Do not edit!
package com.jobando.jobandotrucking.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.jobando.jobandotrucking.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEmployeeWorkingHoursBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView dateToday;

  @NonNull
  public final DigitalClock digitalClock;

  private ActivityEmployeeWorkingHoursBinding(@NonNull LinearLayout rootView,
      @NonNull TextView dateToday, @NonNull DigitalClock digitalClock) {
    this.rootView = rootView;
    this.dateToday = dateToday;
    this.digitalClock = digitalClock;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEmployeeWorkingHoursBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEmployeeWorkingHoursBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_employee_working_hours, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEmployeeWorkingHoursBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dateToday;
      TextView dateToday = ViewBindings.findChildViewById(rootView, id);
      if (dateToday == null) {
        break missingId;
      }

      id = R.id.digitalClock;
      DigitalClock digitalClock = ViewBindings.findChildViewById(rootView, id);
      if (digitalClock == null) {
        break missingId;
      }

      return new ActivityEmployeeWorkingHoursBinding((LinearLayout) rootView, dateToday,
          digitalClock);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
