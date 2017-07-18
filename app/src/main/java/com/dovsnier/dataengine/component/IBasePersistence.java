package com.dovsnier.dataengine.component;

/**
 * Created by lizw on 2017/7/18.
 */

public interface IBasePersistence extends IPersistence {

    String generateIdentifier(String conversationIdentifier);

    String generateForeign(String identifier);
}
