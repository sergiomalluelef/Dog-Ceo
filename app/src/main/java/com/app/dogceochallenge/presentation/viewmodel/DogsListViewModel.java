package com.app.dogceochallenge.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.dogceochallenge.domain.model.DogModel;
import com.app.dogceochallenge.domain.GetDogsUseCase;

import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Gestiona recursos para mostrar lista de razas y subrazas.
 */
public class DogsListViewModel extends BaseViewModel {
    private static final String TAG = "DogsListViewModel";
    private GetDogsUseCase getDogsUseCase;
    private final MutableLiveData<List<DogModel>> notifyList = new MutableLiveData<>();

    public DogsListViewModel(@NonNull Application application) {
        super(application);
        this.getDogsUseCase = new GetDogsUseCase();
    }
    public void getListDogs() {
        if(getInfo().isLoading()){
            return;
        }
        getInfo().setLoading(true);
        getInfo().setShow(false);
        this.getDogsUseCase
                .getListDogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<DogModel>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogModel> list) {
                        if(list.size() == 0){
                            getInfo().setShow(true);
                        }
                        getInfo().setLoading(false);
                        notifyList(list);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        getInfo().setLoading(false);
                        getInfo().setShow(true);
                    }
                });
    }
    public LiveData<List<DogModel>> getNotifyList() {
        return notifyList;
    }

    public void notifyList(List<DogModel> list) {
        notifyList.postValue(list);
    }

}
