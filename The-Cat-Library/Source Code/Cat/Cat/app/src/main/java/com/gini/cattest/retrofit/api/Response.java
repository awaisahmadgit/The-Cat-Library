package com.gini.cattest.retrofit.api;

import java.util.List;

public class Response<T> {

    public interface Listener<T> {
        void onResponse(T response);
    }

    public interface ErrorListener {
        void onErrorResponse(Throwable error);
    }

    public interface ErrorListenerList {
        void onErrorResponse(List<String> errors);
    }

    public interface ListenerSize<T, S> {
        void onResponse(T response, S size);
    }
}
