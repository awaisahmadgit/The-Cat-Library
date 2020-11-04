package com.gini.cattest;

import android.app.Application;
import android.content.Context;


public class CatApplication extends Application {

    public static Context context;
    public static CatApplication appInstance;

    public static synchronized CatApplication getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();
        appInstance = this;
    }
}
