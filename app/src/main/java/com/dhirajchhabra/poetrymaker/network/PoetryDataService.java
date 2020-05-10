package com.dhirajchhabra.poetrymaker.network;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PoetryDataService {

    //create a student of a particular school
    @FormUrlEncoded
    @POST("poetries")
    Call<String> createPoetry(@Query("userId") String userId,
                              @Field("title") String title,
                              @Field("genre") String genre,
                              @Field("body") String body);

    //update a particular student's details of a particular school
    @FormUrlEncoded
    @PUT("poetries/{id}")
    Call<String> updatePoetryById(@Path("id") String id,
                                  @Field("title") String title,
                                  @Field("genre") String genre,
                                  @Field("body") String body);

    //delete particular students of a particular school
    @DELETE("poetries/{id}")
    Call<String> deletePoetryById(@Path("id") String id);
}
