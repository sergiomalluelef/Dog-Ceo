package com.app.dogceochallenge.domain;

import com.app.dogceochallenge.data.DogRepository;
import com.app.dogceochallenge.domain.model.DogImageModel;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
public class GetDogsImageUseCase {
    private final DogRepository repository;

    public GetDogsImageUseCase() {
        this.repository = new DogRepository();
    }

    /**
     * Recupera lista de url para mostrar en vista
     * @param name nombre de raza para descargar urls.
     */
    public Single<DogImageModel> getListDogsImage(String name){
        return this.repository.getAllDogsImageFromApi(name);
    }
}
