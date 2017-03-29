package com.laulee.commonsdk.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by laulee on 17/3/13.
 * 基于Rx的presenter
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if( mCompositeSubscription != null ) {
            mCompositeSubscription.unsubscribe( );
        }
    }

    protected void addSubscrebe( Subscription subscription ) {
        if( mCompositeSubscription == null ) {
            mCompositeSubscription = new CompositeSubscription( );
        }
        mCompositeSubscription.add( subscription );
    }

    @Override
    public void attachView( T view ) {
        this.mView = view;
    }

    @Override
    public void destoryView() {
        this.mView = null;
        unSubscribe( );
    }
}
