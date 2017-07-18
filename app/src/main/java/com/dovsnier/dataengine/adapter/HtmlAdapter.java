package com.dovsnier.dataengine.adapter;

import com.dovsnier.controller.HtmlPersistence;
import com.dovsnier.dataengine.bean.AttributeBean;
import com.dovsnier.dataengine.bean.DocumentBean;
import com.dovsnier.dataengine.component.IHtmlPersistence;
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
}
