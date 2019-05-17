package com.apple.retrofittest;

import android.app.Application;
import android.content.Context;

public class RetrofitApplication extends Application {

    /*用户登陆信息*/
    public static UserData userData = new UserData();

    public static RxSpf_AppPreferences spfapp;
    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitApplication.context = getApplicationContext();
        spfapp = RxSpf_AppPreferences.create(getApplicationContext());



    }
}
