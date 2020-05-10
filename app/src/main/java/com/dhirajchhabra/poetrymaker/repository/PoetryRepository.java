package com.dhirajchhabra.poetrymaker.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dhirajchhabra.poetrymaker.network.PoetryDataService;
import com.dhirajchhabra.poetrymaker.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoetryRepository {

    private static PoetryRepository poetryRepository;
    private MutableLiveData<String> createResponseStatus = new MutableLiveData<>();
    private MutableLiveData<String> updateResponseStatus = new MutableLiveData<>();
    private MutableLiveData<String> deleteResponseStatus = new MutableLiveData<>();

    // Create handle for the RetrofitInstance interface
    private PoetryDataService poetryDataService = RetrofitClientInstance.getRetrofitInstance().create(PoetryDataService.class);

    public static PoetryRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (poetryRepository == null) {
            poetryRepository = new PoetryRepository();
        }
        return poetryRepository;
    }

    public static void clearRepoInstance() {
        poetryRepository = null;
    }

    public LiveData<String> makeNetworkCallForCreatingPoetry(String userId, String title, String genre, String body, String bold, String fontColor, String fontStyle, String fontSize, String italic, String underline) {

        Call<String> callCreatePoetry = poetryDataService.createPoetry(userId, title, genre, body);
        callCreatePoetry.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    createResponseStatus.setValue(response.body());
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

        return createResponseStatus;
    }

    public LiveData<String> makeNetworkCallForUpdatingPoetry(String id, String title, String genre, String body) {

        Call<String> callUpdatePoetry = poetryDataService.updatePoetryById(id, title, genre, body);
        callUpdatePoetry.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    updateResponseStatus.setValue(response.body());
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

        return updateResponseStatus;
    }

    public LiveData<String> makeNetworkCallForDeletingPoetry(String id) {
        Call<String> callDeletePoetry = poetryDataService.deletePoetryById(id);
        callDeletePoetry.enqueue(new Callback<String>() {
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
