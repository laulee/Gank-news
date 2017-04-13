package com.laulee.gank.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.laulee.gank.R;
import com.laulee.gank.base.RxBaseFragment;
import com.laulee.gank.event.NightModeEvent;
import com.laulee.gank.presenter.SettingPresenter;
import com.laulee.gank.utils.RxBus;
import com.laulee.gank.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by laulee on 17/3/31.
 */

public class SettingFragment extends RxBaseFragment<SettingPresenter>
        implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox autoCacheBox;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox noPicStyleBox;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox nightStyleBox;

    @BindView(R.id.ll_setting_feedback)
    LinearLayout llFeedback;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llCacheClear;
    private boolean isNull = true;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initInject() {
        getFragmentComponent( ).inject( this );
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        isNull = savedInstanceState == null;
        super.onCreate( savedInstanceState );
    }

    @Override
    protected void initView( View view ) {
    }

    @Override
    protected void initParams() {
        nightStyleBox.setChecked( SharedPreferenceUtil.getNightModeState( ) );
        autoCacheBox.setOnCheckedChangeListener( this );
        noPicStyleBox.setOnCheckedChangeListener( this );
        nightStyleBox.setOnCheckedChangeListener( this );
    }

    @Override
    public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
        switch( buttonView.getId( ) ) {
            case R.id.cb_setting_cache:

                break;
            case R.id.cb_setting_image:
                break;
            case R.id.cb_setting_night:
                if( isNull ) {
                    SharedPreferenceUtil.setNightModeState( isChecked );
                    NightModeEvent nightModeEvent = new NightModeEvent( );
                    nightModeEvent.setNightMode( isChecked );
                    RxBus.getDefault( ).post( nightModeEvent );
                }
                break;
        }
    }

    @OnClick(R.id.ll_setting_feedback)
    void feedBack() {

    }

    @OnClick(R.id.ll_setting_clear)
    void clearCache() {

    }

}
