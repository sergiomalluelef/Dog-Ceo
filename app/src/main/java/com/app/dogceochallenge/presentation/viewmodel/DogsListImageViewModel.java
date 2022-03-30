package com.app.dogceochallenge.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.dogceochallenge.domain.model.DogImageModel;
import com.app.dogceochallenge.domain.GetDogsImageUseCase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Gestiona recursos para mostrar lista de imagenes segun razas y subrazas
 */
public class DogsListImageViewModel extends BaseViewModel {
    private static final String TAG = "DogsListImageViewModel";
    private GetDogsImageUseCase getDogsImageUseCase;
    private String name;
    private final MutableLiveData<List<String>> notifyList = new MutableLiveData<>();
    public DogsListImageViewModel(@NonNull Application application) {
        super(application);
        this.getDogsImageUseCase = new GetDogsImageUseCase();
    }

    public void getListDogsImage() {
        if(getInfo().isLoading()){
            return;
        }
        getInfo().setLoading(true);
        getInfo().setShow(false);
        this.getDogsImageUseCase
                .getListDogsImage(getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<DogImageModel>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull DogImageModel model) {
                        notifyList(model.getMessage());
                        if(model.getMessage().size() == 0){
                            getInfo().setShow(true);
                        }
                        getInfo().setLoading(false);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        getInfo().setLoading(false);
                        getInfo().setShow(true);
                    }
                });
    }
    public LiveData<List<String>> getNotifyList() {
        return notifyList;
    }

    public void notifyList(List<String> list) {
        notifyList.postValue(list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
