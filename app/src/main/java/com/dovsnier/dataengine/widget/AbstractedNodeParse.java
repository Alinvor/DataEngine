package com.dovsnier.dataengine.widget;

import org.htmlparser.Node;
import org.htmlparser.util.NodeList;

/**
 * Created by lizw on 2017/7/17.
 */

public interface AbstractedNodeParse extends IParse {

    void parseNodeList(NodeList nodeList);

    void parseNode(Node node);

}
