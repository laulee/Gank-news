package com.laulee.gank.presenter;

import com.laulee.commonsdk.base.RxPresenter;
import com.laulee.commonsdk.http.ObserveMap;
import com.laulee.commonsdk.http.RetrofitHelper;
import com.laulee.gank.app.Constants;
import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.http.service.GitHubService;
import com.laulee.gank.presenter.contact.UntieFragmentContact;

import java.util.List;

import okhttp3.OkHttpClient;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/2/27.
 */

public class UniteFragmentPresenter extends RxPresenter<UntieFragmentContact.AndroidFragmentView> {

    public static final String TECH_ANDROID = "Android";
    public static final String TECH_IOS = "iOS";
    public static final String TECH_WEB = "前端";
    public static final String TECH = "tech";
    private static final int NUM_OF_PAGE = 20;

    public void getData( String category, int page ) {
        Subscription subscription = RetrofitHelper.getInstance( )
                .createService( GitHubService.class, Constants.BASE_URL, new OkHttpClient( ) )
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
        Subscription subscription = RetrofitHelper.getInstance( )
                .createService( GitHubService.class, Constants.BASE_URL, new OkHttpClient( ) )
                .getRandomGirl( random ).subscribeOn( Schedulers.io( ) )
                .observeOn( AndroidSchedulers.mainThread( ) ).map( ObserveMap::mapResult )
                .map( this::mapImage ).subscribe( mView::showGirlImage, new Action1<Throwable>( ) {
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
