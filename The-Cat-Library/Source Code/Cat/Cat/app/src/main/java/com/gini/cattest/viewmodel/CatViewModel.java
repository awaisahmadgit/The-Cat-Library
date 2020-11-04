package com.gini.cattest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.gini.cattest.model.CatResult;
import com.gini.cattest.retrofit.ApiCallback;
import com.gini.cattest.retrofit.ApiManager;
import com.gini.cattest.retrofit.api.Response;
import java.util.List;
import okhttp3.Headers;

public class CatViewModel extends AndroidViewModel {

    public CatViewModel(@NonNull Application application) {
        super(application);
    }

    public void getCatData(Response.ListenerSize<List<CatResult>, Integer> listener, Response.ErrorListener errorListener, int pageNo) {
        ApiManager.getInstance(getApplication().getApplicationContext()).getCatImages(pageNo, new ApiCallback<List<CatResult>>() {

            @Override
            public void onSuccess(List<CatResult> response, retrofit2.Response<List<CatResult>> responseObj) {
                Headers headers = responseObj.headers();
                String paginationHeaderStr = headers.get("pagination-count") != null ? headers.get("pagination-count") : "0";
                int paginationCount = Integer.parseInt(paginationHeaderStr);
                listener.onResponse(response, paginationCount);
            }

            @Override
            public void onFail(Throwable throwable) {
                errorListener.onErrorResponse(throwable);
            }
        });

    }
}
