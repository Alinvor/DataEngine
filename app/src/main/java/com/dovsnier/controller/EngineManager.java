package com.dovsnier.controller;

import com.dvsnier.controller.Manager;

/**
 * Created by lizw on 2017/7/14.
 */

public class EngineManager extends Manager {

    protected static final String TAG = EngineManager.class.getSimpleName();
    protected String conversationIdentifier;
    private static final EngineManager instance = new EngineManager();

    protected EngineManager() {
    }

    public static EngineManager getInstance() {
        return instance;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != conversationIdentifier) conversationIdentifier = null;
    }

    public String getConversationIdentifier() {
        return conversationIdentifier;
    }

    public void setConversationIdentifier(String conversationIdentifier) {
        this.conversationIdentifier = conversationIdentifier;
    }
}
