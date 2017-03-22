package com.laulee.gank.presenter;

import com.laulee.gank.base.RxPresenter;
import com.laulee.gank.http.ObserveMap;
import com.laulee.gank.http.RetrofitHelper;
import com.laulee.gank.presenter.contact.FuliFragmentContact;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/3/22.
 */

public class FuliFragmentPresenter extends RxPresenter<FuliFragmentContact.View> {

    public void getFuliImage(String category,int count,int page) {
        Subscription subscription = RetrofitHelper.getGitHubService( ).getGankData( category, count, page )
                .subscribeOn( Schedulers.io( ) ).observeOn( AndroidSchedulers.mainThread( ) )
                .map( ObserveMap::mapResult )
                .subscribe( mView::showContent, new Action1<Throwable>( ) {

                    @Override
                    public void call( Throwable throwable ) {
                        mView.showError( throwable.getMessage( ) );
                    }
                } );
        addSubscrebe( subscription );
    }

}
