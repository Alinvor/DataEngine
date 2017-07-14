package com.dovsnier.controller;

import com.dvsnier.controller.Manager;
import com.dvsnier.widget.LifeCycle;

import org.xutils.DbManager;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineManager implements LifeCycle {

    private static final String TAG = EngineManager.class.getSimpleName();
    private static final EngineManager instance = new EngineManager();

    private Manager manager;

    private EngineManager() {
        manager = Manager.getInstance();
    }

    public static EngineManager getInstance() {
        return instance;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public DbManager getDbManager() {
        return Manager.getInstance().getDbManager();
    }

    @Override
    public void onDestroy() {
        if (null != manager)
            manager.onDestroy();
    }
}
