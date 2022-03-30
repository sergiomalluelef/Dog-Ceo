package com.app.dogceochallenge.domain;

import com.app.dogceochallenge.data.DogRepository;
import com.app.dogceochallenge.domain.model.DogModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
public class GetDogsUseCase {
    private final DogRepository repository;

    public GetDogsUseCase() {
        this.repository = new DogRepository();
    }

    /**
     * Retorna lista de @{@link DogModel} para mostrar en vista
     */
    public Single<List<DogModel>> getListDogs(){
        return this.repository.getAllDogsFromApi();
    }
}
