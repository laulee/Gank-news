package com.laulee.gank.base;

/**
 * Created by laulee on 16/12/25.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView( V view );

    void destoryView();
}
