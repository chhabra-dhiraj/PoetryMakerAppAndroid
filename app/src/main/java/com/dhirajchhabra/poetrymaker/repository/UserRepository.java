package com.dhirajchhabra.poetrymaker.repository;

import android.service.autofill.UserData;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dhirajchhabra.poetrymaker.models.User;
import com.dhirajchhabra.poetrymaker.network.PoetryDataService;
import com.dhirajchhabra.poetrymaker.network.RetrofitClientInstance;
import com.dhirajchhabra.poetrymaker.network.UserDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository userRepository;
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<String> createId = new MutableLiveData<>();
    private MutableLiveData<String> deleteResponseStatus = new MutableLiveData<>();

    // Create handle for the RetrofitInstance interface
    private UserDataService userDataService = RetrofitClientInstance.getRetrofitInstance().create(UserDataService.class);

    public static UserRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public static void clearRepoInstance() {
        userRepository = null;
    }

    public LiveData<User> makeNetworkCallForGettingUser(String id) {

        Call<User> callGetUser = userDataService.getUserById(id);
        callGetUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    user.setValue(response.body());
                } else if (response.code() == 400) {
//                    try {
//                        schoolLoginRequestResponseStatus.setValue(response.errorBody().string());
//                    } catch (IOException e) {
//                        schoolLoginRequestResponseStatus.setValue("There seems to be some problem. Try Again");
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
//                schoolLoginRequestResponseStatus.setValue("fail2");
            }
        });

        return user;
    }

    public LiveData<String> makeNetworkCallForCreatingUser(String firebaseId, String name, String email, String imageUrl) {

        Call<String> callCreateUser = userDataService.createUser(firebaseId, name, email, imageUrl);
        callCreateUser.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    createId.setValue(response.body());
                } else if (response.code() == 400) {
                    Log.e("TAG", "Firebase Ui Activity onChanged: s: response");
//                    try {
//                        schoolLoginRequestResponseStatus.setValue(response.errorBody().string());
//                    } catch (IOException e) {
//                        schoolLoginRequestResponseStatus.setValue("There seems to be some problem. Try Again");
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG", "Firebase Ui Activity onChanged: s: failure " + t.toString());
//                schoolLoginRequestResponseStatus.setValue("fail2");
            }
        });

        return createId;
    }

    public LiveData<String> makeNetworkCallForDeletingUser(String id) {

        Call<String> callDeleteUser = userDataService.deleteUserById(id);
        callDeleteUser.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    deleteResponseStatus.setValue(response.body());
                } else if (response.code() == 400) {
//                    try {
//                        schoolLoginRequestResponseStatus.setValue(response.errorBody().string());
//                    } catch (IOException e) {
//                        schoolLoginRequestResponseStatus.setValue("There seems to be some problem. Try Again");
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                schoolLoginRequestResponseStatus.setValue("fail2");
            }
        });

        return deleteResponseStatus;
    }

}