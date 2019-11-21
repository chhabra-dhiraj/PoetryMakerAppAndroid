package com.dhirajchhabra.poetrymaker.network;

import com.dhirajchhabra.poetrymaker.models.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserDataService {

    @FormUrlEncoded
    @POST("users")
    Call<String> createUser(@Field("firebaseId") String firebaseId,
                            @Field("name") String name,
                            @Field("email") String email,
                            @Field("imageUrl") String imageUrl);

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") String id);

    //delete all students of a particular school
    @DELETE("users/{id}")
    Call<String> deleteUserById(@Path("id") String id);

}
