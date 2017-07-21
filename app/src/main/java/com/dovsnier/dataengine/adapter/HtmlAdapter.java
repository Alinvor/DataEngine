package com.dovsnier.dataengine.adapter;

import com.dovsnier.controller.EngineManager;
import com.dovsnier.controller.HtmlPersistence;
import com.dovsnier.dataengine.bean.AttributeBean;
import com.dovsnier.dataengine.bean.DocumentBean;
import com.dovsnier.dataengine.component.IHtmlPersistence;
import com.dvsnier.utils.StringUtils;
import com.dvsnier.widget.LifeCycle;

/**
 * Created by lizw on 2017/7/18.
 */
public class HtmlAdapter extends AbstractAdapter implements LifeCycle, IHtmlPersistence {

    protected HtmlPersistence persistence;

    public HtmlAdapter() {
        if (null == persistence) persistence = new HtmlPersistence();
    }

    public HtmlAdapter(HtmlPersistence persistence) {
        this.persistence = persistence;
//        if (null == persistence) throw new NullPointerException("persistence can not be null.");
    }

    @Override
    public String generateConversationIdentifier() {
        return null != persistence ? persistence.generateConversationIdentifier() : null;
    }

    @Override
    public String generateIdentifier(String conversationIdentifier) {
        return null != persistence ? persistence.generateIdentifier(conversationIdentifier) : null;
    }

    @Override
    public String generateForeign(String identifier) {
        return null != persistence ? persistence.generateForeign(identifier) : null;
    }

    @Override
    public void saveHead(DocumentBean bean) {
        if (null != persistence)
            persistence.saveHead(bean);
    }

    @Override
    public void saveDocument(DocumentBean bean) {
        if (null != persistence)
            persistence.saveDocument(bean);
    }

    @Override
    public void saveAttribute(AttributeBean bean) {
        if (null != persistence)
            persistence.saveAttribute(bean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != persistence) persistence.onDestroy();
    }

    public HtmlPersistence getPersistence() {
        return persistence;
    }

    public void setPersistence(HtmlPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public boolean isDebug() {
        return null != persistence ? persistence.isDebug() : false;
    }

    @Override
    public void setDebug(boolean debug) {
        super.setDebug(debug);
        if (null != persistence) persistence.setDebug(debug);
    }

    public String setConversationIdentifier() {
        String conversationIdentifier = EngineManager.getInstance().getConversationIdentifier();
        if (null == conversationIdentifier) {
            conversationIdentifier = generateConversationIdentifier();
            EngineManager.getInstance().setConversationIdentifier(conversationIdentifier);
            debug(String.format("the current conversationIdentifier is  %s", conversationIdentifier));
        }
        persistence.setConversationIdentifier(conversationIdentifier);
        return conversationIdentifier;
    }

    public String setNodeIdentifier(String conversationIdentifier, String nodeIdentifier) {
        if (!StringUtils.isNotEmpty(conversationIdentifier))
            throw new NullPointerException("conversationIdentifier cannot be null.");
        String generateIdentifier = null;
        if (null == nodeIdentifier) {
            generateIdentifier = generateIdentifier(conversationIdentifier);
            debug(String.format("the current nodeIdentifier is  %s", generateIdentifier));
        } else {
            generateIdentifier = nodeIdentifier;
        }
        persistence.setNodeIdentifier(generateIdentifier);
        return generateIdentifier;
    }

    public void setNodeForeign(String nodeIdentifier) {
        if (!StringUtils.isNotEmpty(nodeIdentifier)) throw new NullPointerException("nodeIdentifier cannot be null.");
        persistence.setNodeForeign(generateForeign(nodeIdentifier));
    }
}
