package com.app.dogceochallenge.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Sergio MuÃ±oz
 * on 29-03-2022.
 */
public class RetrofitHelper {
    public static final String BASE_DOG_CEO_API = "https://dog.ceo/api/";

    public Retrofit getRetrofit(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return new Retrofit.Builder()
                .baseUrl(BASE_DOG_CEO_API)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }
}