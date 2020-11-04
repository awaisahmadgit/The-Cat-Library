package com.gini.cattest.retrofit;

import com.gini.cattest.BuildConfig;
import com.gini.cattest.retrofit.DatatypeAdapter.DoubleDefaultAdapter;
import com.gini.cattest.retrofit.DatatypeAdapter.IntegerDefaultAdapter;
import com.gini.cattest.retrofit.DatatypeAdapter.LongDefaultAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AppHelper {
    public static boolean isDebugMode() {
        return BuildConfig.DEBUG;
    }

    /**
     * Build an object from the specified JSON resource using Gson.
     *
     * @param type The type of the object to build.
     * @return An object of type T, with member fields populated using Gson.
     */
    private static <T> T constructUsingGson(final Class<T> type, final String jsonString) {
        return AppHelper.getGson().fromJson(jsonString, type);
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(double.class, new DoubleDefaultAdapter())
                .registerTypeAdapter(Double.class, new DoubleDefaultAdapter())
                .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
                .registerTypeAdapter(int.class, new IntegerDefaultAdapter())
                .registerTypeAdapter(Long.class, new LongDefaultAdapter())
                .registerTypeAdapter(long.class, new LongDefaultAdapter())
                .create();
    }
}
