package com.laulee.gank.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.laulee.gank.app.App;

/**
 * Created by laulee on 17/3/1.
 */

public class SystemUtil {

    /**
     * 检测网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance( )
                .getApplicationContext( ).getSystemService( Context.CONNECTIVITY_SERVICE );
        return connectivityManager.getActiveNetworkInfo( ) != null;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px( Context context, float dpValue ) {
        final float scale = context.getResources( ).getDisplayMetrics( ).density;
        return (int) ( dpValue * scale + 0.5f );
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp( Context context, float pxValue ) {
        final float scale = context.getResources( ).getDisplayMetrics( ).density;
        return (int) ( pxValue / scale + 0.5f );
    }
}
