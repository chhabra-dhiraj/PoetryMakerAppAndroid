package com.dhirajchhabra.poetrymaker.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dhirajchhabra.poetrymaker.models.Poem;
import com.dhirajchhabra.poetrymaker.repository.PoemRepository;
import com.dhirajchhabra.poetrymaker.repository.PoetryRepository;

public class PoemViewModel extends ViewModel {

    private LiveData<Poem> poem;
    private PoemRepository poemRepository;

    public void initialize() {
        poemRepository = PoemRepository.getInstance();
    }

    public void clearCurrentData() {
        PoemRepository.clearRepoInstance();
    }

    public LiveData<Poem> makeNetworkCallForPoem(String word, String genre) {
        poem = poemRepository.makeNetworkCallForPoem(word, genre);
        return poem;
    }
}
