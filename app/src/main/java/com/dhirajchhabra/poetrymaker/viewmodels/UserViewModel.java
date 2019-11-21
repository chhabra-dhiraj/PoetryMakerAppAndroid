package com.dhirajchhabra.poetrymaker.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dhirajchhabra.poetrymaker.models.User;
import com.dhirajchhabra.poetrymaker.repository.PoetryRepository;
import com.dhirajchhabra.poetrymaker.repository.UserRepository;

public class UserViewModel extends ViewModel {

    private LiveData<User> user;
    private LiveData<String> createId;
    private LiveData<String> deleteResponseStatus;
    private UserRepository userRepository;

    public void initialize() {
        userRepository = UserRepository.getInstance();
    }

    public void clearCurrentData() {
        UserRepository.clearRepoInstance();
    }

    public LiveData<User> makeNetworkCallForGettingUser(String id) {
        user = userRepository.makeNetworkCallForGettingUser(id);
        return user;
    }

    public LiveData<String> makeNetworkCallForCreatingUser(String firebaseId, String name, String email, String imageUrl) {
        createId = userRepository.makeNetworkCallForCreatingUser(firebaseId, name, email, imageUrl);
        return createId;
    }

    public LiveData<String> makeNetworkCallForDeletingUser(String id) {
        deleteResponseStatus = userRepository.makeNetworkCallForDeletingUser(id);
        return deleteResponseStatus;
    }

}
