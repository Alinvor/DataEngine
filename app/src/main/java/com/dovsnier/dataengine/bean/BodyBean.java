package com.dovsnier.dataengine.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/14.
 */
@Table(name = "body_info")
public class BodyBean extends AbstractNodeBean {

    @Column(name = "id", isId = true, autoGen = true)
    protected int id;
    @Column(name = "identifier")
    protected String identifier;
    @Column(name = "foreign")
    protected String foreign;
    @Column(name = "body")
    protected String body;
    @Column(name = "content")
    protected String content;
    @Column(name = "charset")
    protected String charset;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getForeign() {
        return foreign;
    }

    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
