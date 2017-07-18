package com.dovsnier.controller;

import com.dvsnier.widget.LifeCycle;

/**
 * Created by lizw on 2017/7/18.
 */

public abstract class Document implements LifeCycle {

    protected final String TAG = this.getClass().getSimpleName();
    protected boolean isDebug;

    @Override
    public void onDestroy() {
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }
}
