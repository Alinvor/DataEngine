package com.dovsnier.dataengine.widget;

import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.Text;

/**
 * Created by lizw on 2017/7/17.
 */

public interface INodeParse extends AbstractedNodeParse {

    void parseText(Text node);

    void parseRemark(Remark node);

    void parseTag(Tag node);
}
