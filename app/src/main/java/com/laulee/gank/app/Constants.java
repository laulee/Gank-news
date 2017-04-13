package com.laulee.gank.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by laulee on 17/3/1.
 */

public class Constants {
    //================= PATH ====================

    public static final String PATH_DATA = Environment.getExternalStorageDirectory( )
            .getAbsolutePath( ) + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String BASE_URL = "https://gank.io/api/";
    public static final int ANDROID = 101;
    public static final int IOS = 102;
    public static final int FULI = 103;
    public static final int XIATUIJIAN = 104;
    public static final int APP = 105;
    public static final String GANK_INFO = "gank_info";
    public static final String NIGHT_MODE_STATE = "night_mode_state";
}
