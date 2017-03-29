package com.laulee.gank.presenter;

import com.laulee.commonsdk.base.RxPresenter;
import com.laulee.commonsdk.http.ObserveMap;
import com.laulee.commonsdk.http.RetrofitHelper;
import com.laulee.gank.app.Constants;
import com.laulee.gank.http.service.GitHubService;
import com.laulee.gank.presenter.contact.FuliFragmentContact;

import okhttp3.OkHttpClient;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/3/22.
 */

public class FuliFragmentPresenter extends RxPresenter<FuliFragmentContact.View> {

    /**
     * 获得图片
     *
     * @param category
     * @param count
     * @param page
     */
    public void getFuliImage( String category, int count, int page ) {
        Subscription subscription = RetrofitHelper.getInstance( )
                .createService( GitHubService.class, Constants.BASE_URL, new OkHttpClient( ) )
                .getGankData( category, count, page ).subscribeOn( Schedulers.io( ) )
                .observeOn( AndroidSchedulers.mainThread( ) ).map( ObserveMap::mapResult )
                .subscribe( mView::showContent, new Action1<Throwable>( ) {

                    @Override
                    public void call( Throwable throwable ) {
                        mView.showError( throwable.getMessage( ) );
                    }
                } );
        addSubscrebe( subscription );
    }

}
