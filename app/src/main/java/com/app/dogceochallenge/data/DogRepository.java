package com.app.dogceochallenge.data;

import com.app.dogceochallenge.domain.model.DogImageModel;
import com.app.dogceochallenge.domain.model.DogModel;
import com.app.dogceochallenge.data.network.DogService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
public class DogRepository {
    private DogService dogService;
    public DogRepository() {
        this.dogService = new DogService();
    }
    public Single<List<DogModel>> getAllDogsFromApi(){
        return this.dogService.getListDogs();
    }

    public Single<DogImageModel> getAllDogsImageFromApi(String name){
        return this.dogService.getListImage(name);
    }
}
