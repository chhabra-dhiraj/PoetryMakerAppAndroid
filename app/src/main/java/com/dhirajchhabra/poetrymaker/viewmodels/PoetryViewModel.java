package com.dhirajchhabra.poetrymaker.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dhirajchhabra.poetrymaker.models.Poem;
import com.dhirajchhabra.poetrymaker.repository.PoetryRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoetryViewModel extends ViewModel {

    private LiveData<String> createResponseStatus;
    private LiveData<String> updateResponseStatus;
    private LiveData<String> deleteResponseStatus;
    private PoetryRepository poetryRepository;

    public void initialize() {
        poetryRepository = PoetryRepository.getInstance();
    }

    public void clearCurrentData() {
        PoetryRepository.clearRepoInstance();
    }

    public LiveData<String> makeNetworkCallForCreatingPoetry(String userId, String title, String genre, String body, String bold, String fontColor, String fontStyle, String fontSize, String italic, String underline) {
        createResponseStatus = poetryRepository.makeNetworkCallForCreatingPoetry(userId, title, genre, body, bold, fontColor, fontStyle, fontSize, italic, underline);
        return createResponseStatus;
    }

    public LiveData<String> makeNetworkCallForUpdatingPoetry(String id, String title, String genre, String body, String bold, String fontColor, String fontStyle, String fontSize, String italic, String underline) {
        updateResponseStatus = poetryRepository.makeNetworkCallForUpdatingPoetry(id, title, genre, body, bold, fontColor, fontStyle, fontSize, italic, underline);
        return updateResponseStatus;
    }

    public LiveData<String> makeNetworkCallForDeletingPoetry(String id) {
        deleteResponseStatus = poetryRepository.makeNetworkCallForDeletingPoetry(id);
        return deleteResponseStatus;
    }

}
