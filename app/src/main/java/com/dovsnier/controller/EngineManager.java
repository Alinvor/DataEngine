package com.dovsnier.controller;

import com.dvsnier.controller.Manager;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineManager extends Manager {

    private static final String TAG = EngineManager.class.getSimpleName();
    private static final EngineManager instance = new EngineManager();

    protected EngineManager() {
    }

    public static EngineManager getInstance() {
        return instance;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
