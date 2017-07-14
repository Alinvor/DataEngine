package com.dovsnier.dataengine.application;

import com.dvsnier.application.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
