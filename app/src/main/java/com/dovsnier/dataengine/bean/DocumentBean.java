package com.dovsnier.dataengine.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/17.
 */
@Table(name = "document_info")
public class DocumentBean extends AbstractNodeBean {

    @Column(name = "id", isId = true, autoGen = true)
    protected String id;
    @Column(name = "identifier")
    protected String identifier;
    @Column(name = "foreign")
    protected String foreign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
