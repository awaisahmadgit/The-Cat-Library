# the-cat-library

`the-cat-library` is a fast and effcient way of selecting a cat image from a list of random cat images. This library is intended for use in Android development. 
The library uses `Retrofit` for network layer, `Glide` for image fetching, `RecyclerView` for dynamic listing. This Library won't be
posible without the Cat API (http://thecatapi.com).

## Big Thanks to the great Cat API

Go and visit [http://thecatapi.com](http://thecatapi.com)

![alt text](https://github.com/awaisahmadgit/the-cat-library/blob/main/The-Cat-Library/Library%20(ARR)/TheCatLibrary.png)



**Android compatible**

## Installation

Install using **AAR**.

(1). You can download aar file from [here][1]

(2). Once downloaded, import it into your project using Android Studio -> File -> New -> New Module. At Select Module Type screen, Select Import .JAR/.AAR Package and import aar file.

(3). Goto you build.gradle(module) file and include the library using:
```gradle

dependencies {
  implementation project(':cat-lib')
}
```

## Dependencies

You need to add the following dependencies in your build.gradle(module) file.

```gradle

dependencies {
  implementation project(':cat-lib')
  
  implementation 'com.squareup.retrofit2:retrofit:2.9.0'
  implementation 'com.squareup.okhttp3:okhttp:4.9.0'
  implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'
  implementation 'com.google.code.gson:gson:2.8.6'
  implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
  implementation 'com.github.bumptech.glide:glide:4.11.0'
  implementation 'androidx.appcompat:appcompat:1.2.0'
  implementation 'com.google.android.material:material:1.2.1'
  implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
  implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
}
```

## Manfiest

In your app's mainfest, don't forget to write the following inside your calling Activity tag. 

```manifest
<activity android:name=".CallingActivity"
  tools:replace="android:theme">
```

How do I use the-cat-library?
-------------------

**CatManager** is a Single-ton class resposible for initializing & handling.

## Initialization

First, need to initialize the Cat Manager with parameters for `ActionBar` such as:
* **Title**: String
* **Background Color**: int
* **Show Back Button**: boolean

Initialization will look something like this:

```java
// For a simple view:                                           
CatManager.getInstance(getApplicationContext()).initialize("Select Picture", Color.GRAY, true);
```

## Open Cat Activity

Once, **Initialization** done, we need to start the CatActivity with two `callbacks`

* **onBackPress()**: When the back button is pressed
* **onImageClick(Drawable)**: When the user taps on any cat image for selection, that Image drawable is returned in this callback.

Open CatActivity like this:

```java                                         
CatManager.getInstance(getApplicationContext()).startCatActivity(new CatManagerListener() {
                @Override
                public void onBackPress() {
                }

                @Override
                public void onImageClick(Drawable drawable) {
                // Do anything with the selected cat image
                }
            });
}
```

Compatibility
-------------

 * **Minimum Android SDK**:  requires a minimum API level of 16.
 * **Compile Android SDK**:  requires you to compile against API 26 or later.

Author
------
Awais Ahmad - @awaisahmadgit on GitHub

Disclaimer
---------
This is not a licensed product.

[1]: https://github.com/awaisahmadgit/the-cat-library/blob/main/The-Cat-Library/Library%20(ARR)/cat-lib.aar
