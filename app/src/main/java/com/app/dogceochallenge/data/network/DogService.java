package com.app.dogceochallenge.data.network;
import com.app.dogceochallenge.core.RetrofitHelper;
import com.app.dogceochallenge.domain.model.DogImageModel;
import com.app.dogceochallenge.domain.model.DogModel;
import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
public class DogService {
    RetrofitHelper retrofitHelper = new RetrofitHelper();

    public Single<List<DogModel>> getListDogs() {
        return Single.create(emitter -> {
            try {
                Retrofit retrofit = retrofitHelper.getRetrofit();
                DogApiClient service = retrofit.create(DogApiClient.class);
                Call<JsonNode> repos = service.listDog();
                Response<JsonNode> execute = repos.execute();
                if (!emitter.isDisposed()) {
                    if (execute.isSuccessful() && execute.body() != null && execute.body().hasNonNull("message")) {
                        List<DogModel> dogModelList = new ArrayList<>();
                        JSONObject message = new JSONObject(execute.body().get("message").toString());
                        Iterator<String> keys = message.keys();
                        while (keys.hasNext()) {
                            String name = (String) keys.next();
                            String subNames = "";
                            JSONArray jsonArray = new JSONArray(message.get(name).toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String subName = jsonArray.getString(i);
                                if (subName != null && !subName.isEmpty()) {
                                    subNames += " " + subName + " ";
                                }
                            }
                            dogModelList.add(new DogModel(name, subNames));
                        }
                        emitter.onSuccess(dogModelList);
                    } else {
                        emitter.onError(new Throwable(execute.message()));
                    }
                }
            } catch (Throwable e) {
                if (!emitter.isDisposed()) {
                    emitter.onError(e);
                }
            }
        });
    }

    public Single<DogImageModel> getListImage(String name) {
        return Single.create(emitter -> {
            try {
                Retrofit retrofit = retrofitHelper.getRetrofit();
                DogApiClient service = retrofit.create(DogApiClient.class);
                Call<DogImageModel> repos = service.listImage(name);
                Response<DogImageModel> execute = repos.execute();
                if (!emitter.isDisposed()) {
                    if (execute.isSuccessful() && execute.body() != null) {
                        emitter.onSuccess(execute.body());
                    } else {
                        emitter.onError(new Throwable(execute.message()));
                    }
                }
            } catch (Throwable e) {
                if (!emitter.isDisposed()) {
                    emitter.onError(e);
                }
            }
        });
    }


}
