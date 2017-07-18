package com.dovsnier.controller;

import com.dovsnier.dataengine.component.IBasePersistence;
import com.dovsnier.utils.MD5;
import com.dvsnier.bean.BaseBean;
import com.dvsnier.utils.StringUtils;

import org.xutils.ex.DbException;

/**
 * Created by DovSnier on 2017/7/18.
 */

public class Persistence extends Html implements IBasePersistence {

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

    protected final void saveOrUpdate(BaseBean bean) {
        try {
            EngineManager.getInstance().getDbManager().saveOrUpdate(bean);
        } catch (DbException e) {
            e.printStackTrace();
            error(TAG, e);
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
