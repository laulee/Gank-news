package com.laulee.gank.ui.fuli.fragment;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.laulee.commonsdk.base.BaseRecyclerAdapter;
import com.laulee.commonsdk.base.RxBaseFragment;
import com.laulee.gank.R;
import com.laulee.gank.app.Constants;
import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.presenter.FuliFragmentPresenter;
import com.laulee.gank.presenter.contact.FuliFragmentContact;
import com.laulee.gank.ui.fuli.activity.FuliDetailActivity;
import com.laulee.gank.ui.fuli.adapter.FuliAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by laulee on 17/2/27.
 */

public class FuliFragment extends RxBaseFragment<FuliFragmentPresenter>
        implements FuliFragmentContact.View {

    private static final int SPAN_COUNT = 2;
    @BindView(R.id.fuli_fragment_swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_fuli_fragment)
    RecyclerView recyclerView;
    private FuliAdapter fuliAdapter;
    private List<GankEntity> gankItemEntityList = new ArrayList<>( );
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private String category = "福利";
    private int count = 20;
    private int page = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_fuli_layout;
    }

    @Override
    protected void initParams() {
        super.initParams( );
        mPresenter.getFuliImage( category, count, page );
    }

    @Override
    protected void initView( View rootView ) {
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager( SPAN_COUNT,
                                                                      StaggeredGridLayoutManager
                                                                              .VERTICAL );
        mStaggeredGridLayoutManager.setGapStrategy( StaggeredGridLayoutManager.GAP_HANDLING_NONE );
        recyclerView.setLayoutManager( mStaggeredGridLayoutManager );

        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener( ) {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getFuliImage( category, count, page );
            }
        } );

        fuliAdapter = new FuliAdapter( gankItemEntityList );
        fuliAdapter.setIOnItemClickListener(
                new BaseRecyclerAdapter.IOnItemClickListener<GankEntity>( ) {
                    @Override
                    public void onItemClick( View view, GankEntity entity, int position ) {
                        Intent intent = new Intent( getActivity( ), FuliDetailActivity.class );
                        intent.putExtra( Constants.GANK_INFO, entity );
                        View shareView = view.findViewById( R.id.iv_fuli_picture );
                        //兼容5.0以下
                        ActivityOptionsCompat options = ActivityOptionsCompat
                                .makeSceneTransitionAnimation( getActivity( ), shareView,
                                                               "shareView" );
                        startActivity( intent, options.toBundle( ) );
                    }
                } );
        recyclerView.setAdapter( fuliAdapter );
    }

    @Override
    public void showContent( List<GankEntity> gankItemEntities ) {
        if( swipeRefreshLayout.isRefreshing( ) ) {
            swipeRefreshLayout.setRefreshing( false );
        }
        fuliAdapter.addList( gankItemEntities );
    }

    @Override
    public void showError( String message ) {
        if( swipeRefreshLayout.isRefreshing( ) ) {
            swipeRefreshLayout.setRefreshing( false );
        }
        Toast.makeText( getContext( ), message, Toast.LENGTH_LONG ).show( );
    }

}
