package com.gini.cattest.retrofit;

import android.content.Context;

import com.gini.cattest.model.CatResult;
import com.gini.cattest.utils.Constants;

import java.util.List;



public class ApiManager {

    private static ApiManager apiManager;
    private final ApiInterface phrApi;
    private Context mContext;

    public static ApiManager getInstance(Context context) {
        if (apiManager == null) {
            apiManager = new ApiManager(context);
        }
        return apiManager;
    }

    private ApiManager(Context context) {
        mContext = context;
        phrApi = ServiceFactory.create(context);
    }

    public void getCatImages(int pageNo, ApiCallback<List<CatResult>> apiCallback) {

        phrApi.getCatImages(Constants.IMAGE_SIZE,
                            Constants.IMAGE_LIST_ORDER,
                            Constants.IMAGE_LIST_LIMIT,
                            pageNo).enqueue(apiCallback);
    }

}
