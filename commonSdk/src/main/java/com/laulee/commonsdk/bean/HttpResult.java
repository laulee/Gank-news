package com.laulee.commonsdk.bean;

/**
 * Created by laulee on 17/3/13.
 */

public class HttpResult<T> {

    /**
     * error : false
     * results:
     */

    private boolean error;
    private T results;

    public boolean isError() { return error;}

    public T getResults() {
        return results;
    }
}
