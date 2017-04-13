package com.laulee.gank.presenter;

import android.util.Log;

import com.laulee.gank.base.RxPresenter;
import com.laulee.gank.event.NightModeEvent;
import com.laulee.gank.http.RetrofitHelper;
import com.laulee.gank.presenter.contact.MainContact;
import com.laulee.gank.utils.RxBus;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/3/13.
 */

public class MainPrenster extends RxPresenter<MainContact.MainView> {

    @Inject
    public MainPrenster(RetrofitHelper retrofitHelper) {
        initNightEvent( );
    }

    private void initNightEvent() {
        Subscription subscription = RxBus.getDefault( ).toObservable( NightModeEvent.class )
                .subscribeOn( Schedulers.io( ) ).observeOn( AndroidSchedulers.mainThread( ) )
                .map( new Func1<NightModeEvent, Boolean>( ) {
                    @Override
                    public Boolean call( NightModeEvent nightModeEvent ) {
                        return nightModeEvent.getNightMode( );
                    }
                } ).subscribe( new Observer<Boolean>( ) {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError( Throwable e ) {
                        Log.e( "",e.getMessage() );
                    }

                    @Override
                    public void onNext( Boolean b ) {
                        mView.changeNightMode( b );
                    }
                } );
        addSubscrebe( subscription );
    }
}
