package com.cleancoder.sunshine.app.util;

import java.io.Serializable;

/**
 * Created by Leonid on 19.06.2014.
 */
public abstract class ResultWrapper<T> implements Serializable {

    public abstract boolean containsResult();

    public abstract Throwable getException();

    public abstract T getResult();



    public static ResultWrapper onException(final Throwable exception) {
        return new ResultWrapper() {

            @Override
            public boolean containsResult() {
                return false;
            }

            @Override
            public Throwable getException() {
                return exception;
            }

            @Override
            public Object getResult() {
                throw new IllegalStateException("Unsupported in version with exception");
            }

        };
    }


    public static <E> ResultWrapper create(final E result) {
        return new ResultWrapper<E>() {

            @Override
            public boolean containsResult() {
                return true;
            }

            @Override
            public Throwable getException() {
                throw new IllegalStateException("Unsupported in version with result");
            }

            @Override
            public E getResult() {
                return result;
            }

        };
    }

}
