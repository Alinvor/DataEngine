package com.dovsnier.controller;

import com.dovsnier.dataengine.component.UiThreadListener;
import com.dvsnier.widget.LifeCycle;

/**
 * Created by lizw on 2017/7/18.
 */
public abstract class Html implements LifeCycle {

    protected final String TAG = this.getClass().getSimpleName();
    protected boolean isDebug;
    protected UiThreadListener uiThreadListener;

    protected void runOnUiThread(String msg) {
        if (null != uiThreadListener)
            uiThreadListener.runOnUiThread(msg);
    }

    @Override
    public void onDestroy() {
        if (null != uiThreadListener) uiThreadListener = null;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public UiThreadListener getUiThreadListener() {
        return uiThreadListener;
    }

    public void setUiThreadListener(UiThreadListener uiThreadListener) {
        this.uiThreadListener = uiThreadListener;
    }
}
