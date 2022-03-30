package com.app.dogceochallenge.data.network;

import com.app.dogceochallenge.domain.model.DogImageModel;
import com.fasterxml.jackson.databind.JsonNode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
public interface DogApiClient {
    @GET("breeds/list/all/")
    Call<JsonNode> listDog();

    @GET("breed/{breed}/images/")
    Call<DogImageModel> listImage(@Path("breed") String breed);
}
