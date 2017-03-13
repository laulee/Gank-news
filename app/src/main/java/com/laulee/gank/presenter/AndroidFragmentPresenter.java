package com.laulee.gank.presenter;

import com.laulee.gank.base.RxPresenter;
import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.http.ObserveMap;
import com.laulee.gank.http.RetrofitHelper;
import com.laulee.gank.presenter.contact.AndroidFragmentContact;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragmentPresenter
        extends RxPresenter<AndroidFragmentContact.AndroidFragmentView> {

    public void getData() {
        Subscription subscription = RetrofitHelper.getGitHubService( )
                .getGankData( "Android", 10, 1 )
                .subscribeOn( Schedulers.io( ) )
                .observeOn( AndroidSchedulers.mainThread( ) )
                .map( ObserveMap::mapResult )
                .subscribe( mView::showContent, new Action1<Throwable>( ) {

                    @Override
                    public void call( Throwable throwable ) {
                        mView.showError( throwable.getMessage( ) );
                    }
                } );
        addSubscrebe( subscription );
    }

    public void getImage() {
        Subscription subscription = RetrofitHelper.getGitHubService( ).getRandomGirl( 2 )
                .subscribeOn( Schedulers.io( ) ).observeOn( AndroidSchedulers.mainThread( ) )
                .map( ObserveMap::mapResult ).map( this::mapImage )
                .subscribe( mView::showGirlImage, new Action1<Throwable>( ) {
                    @Override
                    public void call( Throwable throwable ) {
                        mView.showError( throwable.getMessage( ) );
                    }
                } );
        addSubscrebe( subscription );
    }

    private String mapImage( List<GankEntity> gankEntities ) {
        return gankEntities.get( 0 ).getUrl();
    }

}
