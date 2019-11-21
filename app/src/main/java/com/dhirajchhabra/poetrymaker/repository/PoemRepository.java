package com.dhirajchhabra.poetrymaker.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dhirajchhabra.poetrymaker.models.Poem;
import com.dhirajchhabra.poetrymaker.network.PoemDataService;
import com.dhirajchhabra.poetrymaker.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoemRepository {

    private static PoemRepository poemRepository;
    private MutableLiveData<Poem> poem = new MutableLiveData<>();

    // Create handle for the RetrofitInstance interface
    private PoemDataService poemDataService = RetrofitClientInstance.getRetrofitInstance().create(PoemDataService.class);

    public static PoemRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (poemRepository == null) {
            poemRepository = new PoemRepository();
        }
        return poemRepository;
    }

    public static void clearRepoInstance() {
        poemRepository = null;
    }

    public LiveData<Poem> makeNetworkCallForPoem(String word, String genre) {

        Call<Poem> callPoem = poemDataService.getPoem(word, genre);
        callPoem.enqueue(new Callback<Poem>() {
            @Override
            public void onResponse(Call<Poem> call, Response<Poem> response) {
                if (response.code() == 200) {
                    poem.setValue(response.body());
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
            public void onFailure(Call<Poem> call, Throwable t) {
//                schoolLoginRequestResponseStatus.setValue("fail2");
            }
        });

        return poem;
    }

}
