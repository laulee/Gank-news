package com.laulee.gank.presenter;

import com.laulee.gank.base.RxPresenter;
import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.http.ObserveMap;
import com.laulee.gank.http.RetrofitHelper;
import com.laulee.gank.presenter.contact.UntieFragmentContact;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/2/27.
 */

public class UniteFragmentPresenter
        extends RxPresenter<UntieFragmentContact.AndroidFragmentView> {

    public static final String TECH_ANDROID = "Android";
    public static final String TECH_IOS = "iOS";
    public static final String TECH_WEB = "前端";
    private static final int NUM_OF_PAGE = 20;
    public static final String TECH = "tech";

    public void getData( String category, int page ) {
        Subscription subscription = RetrofitHelper.getGitHubService( )
                .getGankData( category, NUM_OF_PAGE, page ).subscribeOn( Schedulers.io( ) )
                .observeOn( AndroidSchedulers.mainThread( ) ).map( ObserveMap::mapResult )
                .subscribe( mView::showContent, new Action1<Throwable>( ) {

                    @Override
                    public void call( Throwable throwable ) {
                        mView.showError( throwable.getMessage( ) );
                    }
                } );
        addSubscrebe( subscription );
    }

    public void getImage( int random ) {
        Subscription subscription = RetrofitHelper.getGitHubService( ).getRandomGirl( random )
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
        return gankEntities.get( 0 ).getUrl( );
    }

}
