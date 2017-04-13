package com.laulee.gank.ui.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.laulee.gank.R;
import com.laulee.gank.app.AppConfig;
import com.laulee.gank.base.RxBaseActivity;
import com.laulee.gank.presenter.MainPrenster;
import com.laulee.gank.presenter.contact.MainContact;
import com.laulee.gank.ui.main.fragment.GankFragment;
import com.laulee.gank.ui.main.fragment.SettingFragment;
import com.laulee.gank.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by laulee on 17/2/26.
 */

public class MainActivity extends RxBaseActivity<MainPrenster> implements MainContact.MainView {
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
    private int currentItem = 0;

    /**
     * 切换fragment
     *
     * @param fragment
     */
    private void switchFragment( Fragment fragment ) {
        FragmentTransaction transaction = getSupportFragmentManager( ).beginTransaction( );
        if( currentFragment != fragment ) {
            transaction.hide( currentFragment ).show( fragment ).commit( );
            currentFragment = fragment;
        }
    }

    @Override
    protected void initParams() {
        setSupportActionBar( toolbar );
        toolbar.setTitle( getResources( ).getString( R.string.app_name ) );
        initFragments( currentItem );
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
                                currentItem = 0;
                                break;
                            case R.id.nav_ios:
                                currentItem = 0;
                            case R.id.nav_fuli:
                                currentItem = 0;
                                break;
                            case R.id.nav_xiatuijian:
                                currentItem = 0;
                                break;
                            case R.id.nav_app:
                                currentItem = 1;
                                break;
                        }
                        switchFragment( fragments.get( currentItem ) );
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
    }

    /**
     * 初始化fragments
     */
    private void initFragments( int position ) {
        FragmentTransaction transaction = getSupportFragmentManager( ).beginTransaction( );
        fragments = new ArrayList<>( );
        fragments.add( new GankFragment( ) );
        fragments.add( new SettingFragment( ) );
        for( int i = 0; i < fragments.size( ); i++ ) {
            transaction.add( R.id.main_layout_frame, fragments.get( i ),
                             fragments.get( i ).getClass( ).getName( ) );
            if( i != position ) {
                transaction.hide( fragments.get( i ) );
            }
        }
        transaction.commit( );
        currentFragment = fragments.get( position );
    }

    @Override
    protected void initInject() {
        getActivityComponent( ).inject( this );
    }

    @Override
    protected int setContentViewId() {
        return R.layout.main_actvity;
    }

    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event ) {
        if( keyCode == KeyEvent.KEYCODE_BACK ) {
            showExitDialog( );
        }
        return super.onKeyDown( keyCode, event );
    }

    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder
                = new android.support.v7.app.AlertDialog.Builder( this );
        builder.setTitle( "提示" );
        builder.setMessage( "确定退出吗" );
        builder.setNegativeButton( "取消", null );
        builder.setPositiveButton( "确定", new DialogInterface.OnClickListener( ) {
            @Override
            public void onClick( DialogInterface dialogInterface, int i ) {
                AppConfig.exitApp( );
            }
        } );
        builder.show( );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( savedInstanceState == null ) {
            SharedPreferenceUtil.setNightModeState( false );
        } else {
            currentItem = 1;
            switchFragment( fragments.get( currentItem ) );
            navigationView.getMenu( ).findItem( R.id.nav_android ).setChecked( false );
            toolbar.setTitle(
                    navigationView.getMenu( ).findItem( R.id.nav_app ).getTitle( ).toString( ) );
        }
    }

    //取消重建保存activity状态，避免fragment切换重叠
    @Override
    protected void onSaveInstanceState( Bundle outState ) {
        //super.onSaveInstanceState( outState );
    }
}
