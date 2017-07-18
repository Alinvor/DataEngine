package com.dovsnier.dataengine.component;

import com.dovsnier.dataengine.bean.AttributeBean;
import com.dovsnier.dataengine.bean.DocumentBean;

/**
 * Created by lizw on 2017/7/18.
 */

public interface IHtmlPersistence extends IDocumentPersistence {

    void saveHead(DocumentBean bean);

    void saveDocument(DocumentBean bean);

    void saveAttribute(AttributeBean bean);

}
