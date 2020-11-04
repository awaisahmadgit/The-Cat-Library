package com.gini.cattest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


public class CatManager {
    private static CatManager catContext;
    private CatManagerListener mListener;
    private static Context context;

    String title;
    int bgColor;
    boolean showBackBtn;

    public CatManager(Context context) {
        this.context = context;
    }

    public static CatManager getInstance(Context contextBase) {
        if (catContext == null) {
            catContext = new CatManager(contextBase);
        }
       context=contextBase;
        return catContext;
    }

    public CatManagerListener getmListener() {
        return mListener;
    }

    public void initialize(String title, int bgColor, boolean showBackBtn) {
        this.title = title;
        this.bgColor = bgColor;
        this.showBackBtn = showBackBtn;
    }

    public void initialize(String title, int bgColor) {
        this.title = title;
        this.bgColor = bgColor;
        this.showBackBtn = false;
    }

    public void initialize(String title) {
        this.title = title;
        this.bgColor = Color.BLACK;
        this.showBackBtn = false;
    }

    public void initialize(int bgColor) {
        this.title = "";
        this.bgColor = bgColor;
        this.showBackBtn = false;
    }


    public void startCatActivity(CatManagerListener catManagerListener) {
        if (!ActivityCat.active) {
            mListener = catManagerListener;

            Intent i = new Intent(context, ActivityCat.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);
        }
    }

}