package com.dhirajchhabra.poetrymaker;

import android.app.Application;

public class MyApplication extends Application {

    private static String userId;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        MyApplication.userId = userId;
    }
}
