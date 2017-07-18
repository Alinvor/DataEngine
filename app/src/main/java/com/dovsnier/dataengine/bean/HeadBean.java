package com.dovsnier.dataengine.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/18.
 */
@Table(name = "head_info")
public class HeadBean extends AbstractNodeBean {

    @Column(name = "id", isId = true, autoGen = true)
    protected int id;
    @Column(name = "identifier")
    protected String identifier;
    @Column(name = "foreign")
    protected String foreign;
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

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
