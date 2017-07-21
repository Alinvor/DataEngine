package com.dovsnier.dataengine.adapter;

import com.dovsnier.controller.HtmlParse;
import com.dovsnier.controller.HttpPersistence;
import com.dovsnier.dataengine.bean.BodyBean;
import com.dovsnier.dataengine.bean.CookieBean;
import com.dovsnier.dataengine.bean.HeaderBean;
import com.dovsnier.dataengine.bean.RequestBean;
import com.dovsnier.dataengine.component.IHttpPersistence;
import com.dvsnier.widget.LifeCycle;

/**
 * Created by lizw on 2017/7/18.
 */
public class HttpAdapter extends AbstractAdapter implements LifeCycle, IHttpPersistence {

    protected HttpPersistence persistence;
    protected HtmlParse htmlParse;

    public HttpAdapter() {
        if (null == persistence) persistence = new HttpPersistence();
    }


    public HttpAdapter(HtmlParse htmlParse) {
        this.htmlParse = htmlParse;
//        if (null == htmlParse) throw new NullPointerException("htmlParse can not be null.");
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
    public void saveRequest(RequestBean bean) {
        if (null != persistence)
            persistence.saveRequest(bean);
    }

    @Override
    public void saveHeader(HeaderBean bean) {
        if (null != persistence)
            persistence.saveHeader(bean);
    }

    @Override
    public void saveCookie(CookieBean bean) {
        if (null != persistence)
            persistence.saveCookie(bean);
    }

    @Override
    public void saveBody(BodyBean bean) {
        if (null != persistence)
            persistence.saveBody(bean);
        if (null == htmlParse) {
            htmlParse = new HtmlParse(bean.getForeign(), bean.getContent(), null);
        } else {
            htmlParse.setValue(bean.getContent());
        }
        htmlParse.htmlParse();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != persistence) persistence.onDestroy();
        if (null != htmlParse) htmlParse.onDestroy();
    }

    public HttpPersistence getPersistence() {
        return persistence;
    }

    public void setPersistence(HttpPersistence persistence) {
        this.persistence = persistence;
    }

    public HtmlParse getHtmlParse() {
        return htmlParse;
    }

    public void setHtmlParse(HtmlParse htmlParse) {
        this.htmlParse = htmlParse;
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
