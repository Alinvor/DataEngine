package com.dovsnier.dataengine.application;

import android.os.Build;

import com.dovsnier.controller.EngineManager;
import com.dvsnier.application.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineApplication extends BaseApplication {

    protected static EngineApplication instance;

    public static EngineApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        EngineManager.getInstance().initialized(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }
}
