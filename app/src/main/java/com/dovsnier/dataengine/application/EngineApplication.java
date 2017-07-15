package com.dovsnier.dataengine.application;

import com.dovsnier.controller.EngineManager;
import com.dvsnier.application.BaseApplication;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        EngineManager.getInstance().initialized(this);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
