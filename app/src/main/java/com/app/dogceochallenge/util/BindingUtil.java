package com.app.dogceochallenge.util;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingUtil {
    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }

}
