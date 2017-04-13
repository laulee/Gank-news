package com.laulee.gank.event;

/**
 * Created by laulee on 17/3/31.
 */

public class NightModeEvent {

    private boolean isNightMode;

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }
}
