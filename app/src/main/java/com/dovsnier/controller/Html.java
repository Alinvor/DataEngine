package com.dovsnier.controller;

import com.dovsnier.dataengine.component.UiThreadListener;
import com.dvsnier.utils.LogUtil;



/**
 * Created by lizw on 2017/7/18.
 */
public abstract class Html extends Document {

    protected UiThreadListener uiThreadListener;
    protected MonitorFeedback monitorFeedback;

    public void runOnUiThread(String msg) {
        if (null != uiThreadListener)
            uiThreadListener.runOnUiThread(msg);
    }

    public void info(String message) {
        if (null != monitorFeedback)
            monitorFeedback.info(message);
        if (isDebug) {
            LogUtil.i(TAG, message);
        }
    }

    public void debug(String message) {
        if (isDebug) {
            if (null != monitorFeedback)
                monitorFeedback.debug(message);
            LogUtil.d(TAG, message);
        }
    }

    public void warning(String message) {
        if (null != monitorFeedback)
            monitorFeedback.warning(message);
        if (isDebug)
            LogUtil.w(TAG, message);
    }

    public void error(String message, Exception e) {
        if (null != monitorFeedback)
            monitorFeedback.error(message, e);
        if (isDebug)
            LogUtil.e(TAG, String.format("%s,%s", message, e.getMessage()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != uiThreadListener) uiThreadListener = null;
        if (null != monitorFeedback) monitorFeedback = null;
    }

    public UiThreadListener getUiThreadListener() {
        return uiThreadListener;
    }

    public void setUiThreadListener(UiThreadListener uiThreadListener) {
        this.uiThreadListener = uiThreadListener;
    }

    public MonitorFeedback getMonitorFeedback() {
        return monitorFeedback;
    }

    public void setMonitorFeedback(MonitorFeedback monitorFeedback) {
        this.monitorFeedback = monitorFeedback;
    }
}
