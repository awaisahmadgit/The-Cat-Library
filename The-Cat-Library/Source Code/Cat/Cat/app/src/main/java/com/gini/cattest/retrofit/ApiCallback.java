package com.gini.cattest.retrofit;

import javax.net.ssl.HttpsURLConnection;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    private String log = "";

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        log += "HTTP: [" + response.code() + "]\n" +
                "HTTP Message: [" + response.message() + "]\n" +
                "URL: [" + call.request().url().toString() + "]\n" +
                "Method: [" + call.request().method() + "]\n" +
                "Host: [" + call.request().url().host() + "]\n" +
                "Query: [" + call.request().url().query() + "]\n";

        switch (response.code()) {
            case HttpsURLConnection.HTTP_OK:
            case HttpsURLConnection.HTTP_CREATED:
            case HttpsURLConnection.HTTP_ACCEPTED:
            case HttpsURLConnection.HTTP_NOT_AUTHORITATIVE:

                if (response.body() != null) {
                    onSuccess(response.body(), response);
                }
                break;
            case HttpsURLConnection.HTTP_FORBIDDEN:
            case HttpsURLConnection.HTTP_UNAUTHORIZED:
                doSessionTimeout();
                break;
            default:
                onFail(new Throwable(response.code() + " " + response.message()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        doError(t.getMessage());
    }

    public abstract void onSuccess(T response, Response<T> responseObj);


    public abstract void onFail(Throwable throwable);

    private void doSessionTimeout() {
    }

    private void doError(String error) {
        onFail(new Throwable(error));
    }

}

