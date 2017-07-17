package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;

/**
 * Created by lizw on 2017/7/17.
 */
public class AbstractNodeBean extends BaseBean {

    @Column(name = "type")
    protected String type;
    @Column(name = "name")
    protected String name;
    @Column(name = "attribute")
    protected String attribute;
    @Column(name = "value")
    protected String value;
    @Column(name = "position")
    protected long position;
    @Column(name = "index")
    protected long index;
    @Column(name = "isDelete")
    protected boolean isDelete;
    @Column(name = "invalid")
    protected boolean invalid;
    @Column(name = "remark")
    protected String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
