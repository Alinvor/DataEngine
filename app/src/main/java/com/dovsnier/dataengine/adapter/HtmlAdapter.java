package com.dovsnier.dataengine.adapter;

import com.dovsnier.controller.HtmlParse;
import com.dovsnier.controller.HtmlPersistence;
import com.dovsnier.dataengine.bean.AttributeBean;
import com.dovsnier.dataengine.bean.BodyBean;
import com.dovsnier.dataengine.bean.HeadBean;
import com.dovsnier.dataengine.bean.DocumentBean;

/**
 * Created by lizw on 2017/7/18.
 */

public class HtmlAdapter implements DocumentAdapter {

    protected final String TAG = this.getClass().getSimpleName();
    protected HtmlParse htmlParse;
    protected HtmlPersistence htmlPersistence;


    public HtmlAdapter(HtmlPersistence htmlPersistence) {
        this.htmlPersistence = htmlPersistence;
    }

    public HtmlAdapter(HtmlParse htmlParse, HtmlPersistence htmlPersistence) {
        this.htmlParse = htmlParse;
        this.htmlPersistence = htmlPersistence;
    }

    @Override
    public void saveNode(DocumentBean bean) {

    }

    @Override
    public void saveAttribute(AttributeBean bean) {

    }

    @Override
    public void saveHead(HeadBean bean) {
        String conversationIdentifier = htmlPersistence.getConversationIdentifier();
//        bean.set
    }

    @Override
    public void saveBody(BodyBean bean) {
        htmlPersistence.getConversationIdentifier();


    }

    public HtmlParse getHtmlParse() {
        return htmlParse;
    }

    public void setHtmlParse(HtmlParse htmlParse) {
        this.htmlParse = htmlParse;
    }

    public HtmlPersistence getHtmlPersistence() {
        return htmlPersistence;
    }

    public void setHtmlPersistence(HtmlPersistence htmlPersistence) {
        this.htmlPersistence = htmlPersistence;
    }
}
