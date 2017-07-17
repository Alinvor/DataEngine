package com.dovsnier.dataengine.widget;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 * Created by lizw on 2017/7/17.
 */

public interface INodeParse extends AbstractedNodeParse {

    void parseText(TextNode node);

    void parseElement(Element node);

    void parseDocumentType(DocumentType node);

    void parseComment(Comment node);

    void parseDataNode(DataNode node);

    void parseAttributes(Node node);

}
