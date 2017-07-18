package com.dovsnier.dataengine.component;

/**
 * Created by lizw on 2017/7/18.
 */

public interface INodePersistence extends IHtmlPersistence {


    String generateIdentifier(String conversationIdentifier);

    String generateForeign(String identifier);

}
