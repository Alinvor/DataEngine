package com.dovsnier.dataengine.widget;

import org.htmlparser.NodeFilter;

/**
 * Created by lizw on 2017/7/17.
 */

public interface IHtmlParse extends IParse {

    void htmlParse();

    void htmlParse(String value, String charset);

    void htmlParse(String value, String charset, NodeFilter filter);

}
