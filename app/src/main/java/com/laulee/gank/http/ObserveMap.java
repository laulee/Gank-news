package com.laulee.gank.http;

import com.laulee.gank.bean.HttpResult;

/**
 * Created by laulee on 17/3/13.
 */

public class ObserveMap {

    public static <T> T mapResult( HttpResult<T> httpResult ) {
        return httpResult.getResults( );
    }
}
