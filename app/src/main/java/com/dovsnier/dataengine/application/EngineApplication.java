package com.dovsnier.dataengine.application;

import com.dovsnier.controller.EngineManager;
import com.dvsnier.application.BaseApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineApplication extends BaseApplication {

    protected static EngineApplication instance;
    protected RefWatcher refWatcher;

    public static EngineApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        EngineManager.getInstance().initialized(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }


    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public void setRefWatcher(RefWatcher refWatcher) {
        this.refWatcher = refWatcher;
    }
}
