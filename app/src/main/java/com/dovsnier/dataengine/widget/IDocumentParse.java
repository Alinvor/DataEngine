package com.dovsnier.dataengine.widget;

import org.jsoup.nodes.Element;

/**
 * Created by lizw on 2017/7/18.
 */

public interface IDocumentParse extends IHtmlParse {

    void parseHead(Element head);

    void parseBody(Element body);

}
