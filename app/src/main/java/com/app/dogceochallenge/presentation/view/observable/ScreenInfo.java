package com.app.dogceochallenge.presentation.view.observable;

import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.app.dogceochallenge.BR;

/**
 * Observable destinado a manejar acciones de carga y mensajes en pantalla.
 */
public class ScreenInfo extends BaseObservable {
    private String message = "No se encuentran datos";
    private Drawable drawable;
    private boolean show = false;
    private boolean loading = false;

    public ScreenInfo(String message, Drawable drawable, boolean show) {
        this.message = message;
        this.drawable = drawable;
        this.show = show;
    }

    public ScreenInfo(Drawable drawable, boolean show) {
        this.drawable = drawable;
        this.show = show;
    }

    public ScreenInfo(Drawable drawable) {
        this.drawable = drawable;
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    @Bindable
    public Drawable getDrawable() {
        return drawable;
    }
    @Bindable
    public boolean isShow() {
        return show;
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }
    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        notifyPropertyChanged(BR.drawable);
    }

    public void setShow(boolean show) {
        this.show = show;
        notifyPropertyChanged(BR.show);
    }
}

