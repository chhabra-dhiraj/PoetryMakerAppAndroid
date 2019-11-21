package com.dhirajchhabra.poetrymaker.network;

import com.dhirajchhabra.poetrymaker.models.Poem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PoemDataService {

    @GET("poems")
    Call<Poem> getPoem(@Query("word") String word,
                       @Query("genre") String genre);

}
