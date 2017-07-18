package com.dovsnier.controller;

import com.dovsnier.dataengine.component.IHtmlPersistence;
import com.dovsnier.dataengine.component.IHttpPersistence;
import com.dovsnier.utils.MD5;
import com.dvsnier.utils.StringUtils;

/**
 * Created by lizw on 2017/7/18.
 */

public class HtmlPersistence extends Html implements IHttpPersistence, IHtmlPersistence {

    protected String conversationIdentifier;
    protected String nodeIdentifier;
    protected String nodeForeign;

    @Override
    public String generateConversationIdentifier() {
        if (!StringUtils.isNotEmpty(conversationIdentifier))
            conversationIdentifier = MD5.obtainDefaultValue();
        return conversationIdentifier;
    }

    @Override
    public String generateIdentifier(String conversationIdentifier) {
        if (StringUtils.isNotEmpty(nodeIdentifier)) {
            String identifier = MD5.obtainDefaultValue(conversationIdentifier);
            return identifier;
        } else {
            nodeIdentifier = MD5.obtainDefaultValue(conversationIdentifier);
            return nodeIdentifier;
        }
    }

    @Override
    public String generateForeign(String identifier) {
        if (StringUtils.isNotEmpty(nodeForeign)) {
            String foreign = MD5.obtainDefaultValue(identifier);
            return foreign;
        } else {
            nodeForeign = MD5.obtainDefaultValue(identifier);
            return nodeForeign;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != conversationIdentifier) conversationIdentifier = null;
        if (null != nodeIdentifier) nodeIdentifier = null;
        if (null != nodeForeign) nodeForeign = null;
    }

    public String getConversationIdentifier() {
        if (null == conversationIdentifier)
            conversationIdentifier = generateConversationIdentifier();
        return conversationIdentifier;
    }

    public void setConversationIdentifier(String conversationIdentifier) {
        this.conversationIdentifier = conversationIdentifier;
    }

    public String getNodeIdentifier() {
        return nodeIdentifier;
    }

    public void setNodeIdentifier(String nodeIdentifier) {
        this.nodeIdentifier = nodeIdentifier;
    }

    public String getNodeForeign() {
        return nodeForeign;
    }

    public void setNodeForeign(String nodeForeign) {
        this.nodeForeign = nodeForeign;
    }
}
