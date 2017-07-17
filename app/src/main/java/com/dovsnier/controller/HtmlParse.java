package com.dovsnier.controller;

import com.dovsnier.dataengine.component.UiThreadListener;
import com.dovsnier.dataengine.widget.IHtmlParse;
import com.dovsnier.dataengine.widget.INodeParse;
import com.dvsnier.utils.LogUtil;
import com.dvsnier.utils.StringUtils;
import com.dvsnier.widget.LifeCycle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lizw on 2017/7/17.
 */
public class HtmlParse implements IHtmlParse, INodeParse, LifeCycle {

    protected static final String TAG = HtmlParse.class.getSimpleName();
    protected boolean isDebug;
    protected String value;
    protected String charset;
    protected UiThreadListener uiThreadListener;
    protected String default_charset = "UTF-8";

    public HtmlParse() {
    }

    public HtmlParse(String value, String charset) {
        this.value = value;
        this.charset = charset;
    }

    @Override
    public void htmlParse() {
        htmlParse(getValue(), getCharset());
    }

    @Override
    public void htmlParse(String value, String charset) {
        if (!StringUtils.isNotEmpty(value)) throw new NullPointerException("value is not empty.");
        try {
            String valueUTF_8 = new String(value.getBytes(StringUtils.isNotEmpty(charset) ? charset : default_charset));
            Document document = Jsoup.parse(valueUTF_8);
            executeParse(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            runOnUiThread(e.getMessage());
        }
    }

    protected void executeParse(Document document) {
        if (isDebug)
            LogUtil.w(TAG, String.format("%s", document.charset().displayName()));
        parseNodeList(document.childNodes());
    }

    @Override
    public void parseNodeList(List<Node> nodeList) {
        if (null == nodeList) return;
        for (Node node : nodeList) {
            int childNodeSize = node.childNodeSize();
            if (childNodeSize > 0) {
                parseNodeList(node.childNodes());
            } else {
                parseNode(node);
            }
        }
    }

    @Override
    public void parseNode(Node node) {
        if (null == node) return;
        LogUtil.d(TAG, "========================================================================");
        LogUtil.d(TAG, String.format("nodeName:%-10s,className:%s", node.nodeName(), node.getClass().getSimpleName()));
        if (node instanceof TextNode) {
            parseText((TextNode) node);
        } else if (node instanceof Element) {
            parseElement((Element) node);
        } else if (node instanceof DocumentType) {
            parseDocumentType((DocumentType) node);
        } else if (node instanceof Comment) {
            parseComment((Comment) node);
        } else if (node instanceof DataNode) {
            parseDataNode((DataNode) node);
        } else {
            LogUtil.d(TAG, String.format("->%s", node.getClass().getSimpleName()));
            runOnUiThread("notice");
        }
        parseAttributes(node);
    }

    @Override
    public void parseText(TextNode node) {
        String nodeName = node.nodeName();
        String wholeText = node.getWholeText();
    }

    @Override
    public void parseElement(Element node) {
        String nodeName = node.nodeName();
        String tagName = node.tagName();
    }

    @Override
    public void parseDocumentType(DocumentType node) {
        String nodeName = node.nodeName();

    }

    @Override
    public void parseComment(Comment node) {
        String nodeName = node.nodeName();
    }

    @Override
    public void parseDataNode(DataNode node) {
        String nodeName = node.nodeName();
    }

    @Override
    public void parseAttributes(Node node) {
        Attributes attributes = node.attributes();
        if (null != attributes) {
            final int size = attributes.size();
            Iterator<Attribute> iterator = attributes.iterator();
            while (iterator.hasNext()) {
                Attribute attribute = iterator.next();
                String key = attribute.getKey();
                String value = attribute.getValue();
                LogUtil.d(TAG, String.format("size:%s,key:%s,value:%s", size, key, value));
            }
        }
    }


    protected void runOnUiThread(String msg) {
        if (null != uiThreadListener)
            uiThreadListener.runOnUiThread(msg);
    }

    @Override
    public void onDestroy() {
        if (null != value) value = null;
        if (null != charset) charset = null;
        if (null != uiThreadListener) uiThreadListener = null;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public UiThreadListener getUiThreadListener() {
        return uiThreadListener;
    }

    public void setUiThreadListener(UiThreadListener uiThreadListener) {
        this.uiThreadListener = uiThreadListener;
    }
}
