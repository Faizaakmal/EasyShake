// Generated by view binder compiler. Do not edit!
package com.mobkom.easyshake.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.mobkom.easyshake.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityListDrinkBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final RecyclerView rvListDrink;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvCategories;

  private ActivityListDrinkBinding(@NonNull CoordinatorLayout rootView,
      @NonNull RecyclerView rvListDrink, @NonNull Toolbar toolbar, @NonNull TextView tvCategories) {
    this.rootView = rootView;
    this.rvListDrink = rvListDrink;
    this.toolbar = toolbar;
    this.tvCategories = tvCategories;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityListDrinkBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityListDrinkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_list_drink, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityListDrinkBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvListDrink;
      RecyclerView rvListDrink = rootView.findViewById(id);
      if (rvListDrink == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tvCategories;
      TextView tvCategories = rootView.findViewById(id);
      if (tvCategories == null) {
        break missingId;
      }

      return new ActivityListDrinkBinding((CoordinatorLayout) rootView, rvListDrink, toolbar,
          tvCategories);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
