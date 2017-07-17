package com.dovsnier.controller;

import com.dovsnier.dataengine.component.UiThreadListener;
import com.dovsnier.dataengine.widget.IHtmlParse;
import com.dovsnier.dataengine.widget.INodeParse;
import com.dvsnier.utils.LogUtil;
import com.dvsnier.utils.StringUtils;
import com.dvsnier.widget.LifeCycle;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * Created by lizw on 2017/7/17.
 */
@Deprecated
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
        htmlParse(getValue(), getCharset(), null);
    }

    @Override
    public void htmlParse(String value, String charset) {
        htmlParse(getValue(), getCharset(), null);
    }

    @Override
    public void htmlParse(String value, String charset, NodeFilter filter) {
        if (!StringUtils.isNotEmpty(value)) throw new NullPointerException("value is not empty.");
        try {
            String valueUTF_8 = new String(value.getBytes(StringUtils.isNotEmpty(charset) ? charset : default_charset));
            Parser parser = Parser.createParser(valueUTF_8, charset);
            if (isDebug)
                LogUtil.w(TAG, parser.getEncoding());
            NodeList nodeList = parser.parse(filter);
            parseNodeList(nodeList);
        } catch (ParserException e) {
            e.printStackTrace();
            runOnUiThread(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            runOnUiThread(e.getMessage());
        }
    }

    @Override
    public void parseNodeList(NodeList nodeList) {
        if (null == nodeList) return;
        SimpleNodeIterator simpleNodeIterator = nodeList.elements();
        if (null != simpleNodeIterator) {
            while (simpleNodeIterator.hasMoreNodes()) {
                Node node = simpleNodeIterator.nextNode();
                parseNode(node);
            }
        }
    }

    @Override
    public void parseNode(Node node) {
        if (null == node) return;
        NodeList nodeChildren = node.getChildren();
        if (null == nodeChildren) {
            LogUtil.d(TAG, "========================================================================");
            LogUtil.d(TAG, String.format("->%s,%s,%s", node.getClass().getSimpleName(), node.getText().equals("\n") ? "" : node.getText(), node.toHtml().equals("\n") ? "" : node.toHtml()));
            if (node instanceof Tag) {
                parseTag((Tag) node);
            } else if (node instanceof Text) {
                parseText((Text) node);
            } else if (node instanceof Remark) {
                parseRemark((Remark) node);
            } else {
                LogUtil.d(TAG, String.format("->%s,%s", node.getClass().getSimpleName(), "WhiteSpace"));
                runOnUiThread("notice");
            }
        } else {
            parseNodeList(nodeChildren);
        }
    }

    @Override
    public void parseRemark(Remark node) {
        Remark remark = node;
        String remarkText = remark.getText();
        LogUtil.d(TAG, String.format("Remark: %s", remarkText));

    }

    @Override
    public void parseText(Text node) {
        Text text = node;
        String textText = text.getText();
        if (text instanceof TextNode) {
            TextNode textNode = (TextNode) text;
            String textNodeText = textNode.getText();
            NodeList textNodeChildren = textNode.getChildren();
            Node textNodeParent = textNode.getParent();
            LogUtil.d(TAG, String.format("goto -> TAG: %s", node.getPage().getText(node.getStartPosition(), node.getEndPosition())));
            if (null != textNodeParent) {
                String textNodeParentText = textNodeParent.getText();
                if (textNodeParent instanceof Tag) {
                    LogUtil.d(TAG, String.format("goto -> TAG: %s", textNodeParent.getClass().getSimpleName()));
                    parseTag((Tag) textNodeParent);
                }
            }
        }
    }

    @Override
    public void parseTag(Tag node) {
        Tag tag = node;
        String tagName = tag.getTagName();
        String rawTagName = tag.getRawTagName();
        Vector vector = tag.getAttributesEx();
        if (tag instanceof TagNode) {
            TagNode tagNode = (TagNode) tag;
            String tagNodeTagName = tagNode.getTagName();
            String tagNodeRawTagName = tagNode.getRawTagName();
            Vector tagNodeAttributesEx = tagNode.getAttributesEx();
        }
        StringBuilder sb = new StringBuilder();
        for (Object item : vector) {
            sb.append(item);
        }
        LogUtil.d(TAG, String.format("TAG: %s,%s,%s", tagName, rawTagName, sb.toString()));
        LogUtil.w(TAG, String.format("TAG: %s", node.getPage().getText(node.getStartPosition(), node.getEndPosition())));
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
