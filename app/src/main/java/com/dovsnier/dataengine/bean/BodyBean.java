package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/14.
 */
@Table(name = "body_info")
public class BodyBean extends BaseBean {

    @Column(name = "id", isId = true, autoGen = true)
    protected int id;
    @Column(name = "identifier")
    protected String identifier;
    @Column(name = "foreign")
    protected String foreign;
    @Column(name = "position")
    protected long position;
    @Column(name = "body")
    protected String body;
    @Column(name = "content")
    protected String content;
    @Column(name = "charset")
    protected String charset;
    @Column(name = "isDelete")
    protected boolean isDelete;
    @Column(name = "invalid")
    protected boolean invalid;


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

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }
}
