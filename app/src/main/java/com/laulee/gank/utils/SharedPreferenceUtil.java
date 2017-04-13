package com.laulee.gank.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.laulee.gank.app.App;
import com.laulee.gank.app.Constants;

/**
 * Created by laulee on 17/3/31.
 */

public class SharedPreferenceUtil {

    private final static String SHARE_NAME = "app_share";
    private final static boolean NIGHT_MODE_STATE = false;

    static SharedPreferences getSharePreferences() {
        return App.getInstance( ).getSharedPreferences( SHARE_NAME, Context.MODE_PRIVATE );
    }

    public static boolean getNightModeState() {
        return getSharePreferences( ).getBoolean( Constants.NIGHT_MODE_STATE, NIGHT_MODE_STATE );
    }

    public static void setNightModeState( boolean state ) {
        getSharePreferences( ).edit( ).putBoolean( Constants.NIGHT_MODE_STATE, state ).apply( );
    }
}
