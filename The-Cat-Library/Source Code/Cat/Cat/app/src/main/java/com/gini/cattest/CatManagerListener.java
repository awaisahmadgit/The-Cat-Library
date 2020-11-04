package com.gini.cattest;

import android.graphics.drawable.Drawable;
import android.media.Image;

public interface CatManagerListener {
    void onBackPress();
    void onImageClick(Drawable drawable);
}
