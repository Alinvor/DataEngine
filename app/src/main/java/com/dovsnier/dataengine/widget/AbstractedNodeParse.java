package com.dovsnier.dataengine.widget;


import org.jsoup.nodes.Node;

import java.util.List;

/**
 * Created by lizw on 2017/7/17.
 */

public interface AbstractedNodeParse extends IParse {

    void parseNodeList(List<Node> nodeList);

    void parseNode(Node node);

}
