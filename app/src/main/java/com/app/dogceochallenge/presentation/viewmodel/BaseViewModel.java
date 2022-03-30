package com.app.dogceochallenge.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;

import com.app.dogceochallenge.R;
import com.app.dogceochallenge.presentation.view.observable.ScreenInfo;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
public class BaseViewModel extends AndroidViewModel implements androidx.databinding.Observable {
    private static String TAG = "BaseViewModel";
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    private CompositeDisposable disposables = new CompositeDisposable();
    private ScreenInfo info;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        info = new ScreenInfo(ContextCompat.getDrawable(getApplication(), R.drawable.ic_pets));
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }
    public void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }
    public void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }

    public void addDisposable(Disposable d){
        disposables.add(d);
    }

    public void removeDisposable(Disposable d){
        disposables.remove(d);
    }

    public ScreenInfo getInfo() {
        return info;
    }

    public void setInfo(ScreenInfo info) {
        this.info = info;
    }
}
