package com.laulee.gank.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.laulee.gank.R;
import com.laulee.gank.base.BaseActivity;
import com.laulee.gank.base.BasePresenter;
import com.laulee.gank.presenter.MainPrenster;
import com.laulee.gank.ui.android.GankFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by laulee on 17/2/26.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_layout_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_layout_navigation)
    NavigationView navigationView;
    @BindView(R.id.main_layout)
    DrawerLayout mDrawLayout;
    private Fragment currentFragment;
    private List<Fragment> fragments;
    private MenuItem mLastMenuItem;
    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * 切换fragment
     *
     * @param fragment
     */
    private void switchFragment( Fragment fragment ) {
        FragmentTransaction transaction = getSupportFragmentManager( ).beginTransaction( );
        if( fragment != currentFragment ) {
            if( !fragment.isAdded( ) ) {
                transaction.add( R.id.main_layout_frame, fragment ).commitAllowingStateLoss( );
            } else {
                transaction.hide( currentFragment ).show( fragment );
            }
            currentFragment = fragment;
        }
    }

    @Override
    protected void initParams() {
        setSupportActionBar( toolbar );
        toolbar.setTitle( getResources( ).getString( R.string.app_name ) );
        initFragments( );
        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawLayout, toolbar, R.string.drawer_open,
                                                   R.string.drawer_close );
        mDrawerToggle.syncState( );
        mDrawLayout.addDrawerListener( mDrawerToggle );

        navigationView.setCheckedItem( R.id.nav_android );
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener( ) {
                    @Override
                    public boolean onNavigationItemSelected( @NonNull MenuItem item ) {
                        switch( item.getItemId( ) ) {
                            case R.id.nav_android:
                                switchFragment( fragments.get( 0 ) );
                                break;
                            case R.id.nav_ios:
                                switchFragment( fragments.get( 1 ) );
                                break;
                            case R.id.nav_fuli:
                                switchFragment( fragments.get( 2 ) );
                                break;
                            case R.id.nav_xiatuijian:
                                switchFragment( fragments.get( 3 ) );
                                break;
                            case R.id.nav_app:
                                switchFragment( fragments.get( 4 ) );
                                break;
                        }
                        if( mLastMenuItem != null ) {
                            mLastMenuItem.setChecked( false );
                        }
                        mLastMenuItem = item;
                        item.setChecked( true );
                        toolbar.setTitle( item.getTitle( ) );
                        mDrawLayout.closeDrawers( );
                        return true;
                    }
                } );

        switchFragment( fragments.get( 0 ) );
    }

    /**
     * 初始化fragment
     */
    private void initFragments() {
        fragments = new ArrayList<>( );
        fragments.add( new GankFragment( ) );
        fragments.add( new GankFragment( ) );
        fragments.add( new GankFragment( ) );
        fragments.add( new GankFragment( ) );
        fragments.add( new GankFragment( ) );
    }

    @Override
    protected int setContentViewId() {
        return R.layout.main_actvity;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MainPrenster( );
    }
}
