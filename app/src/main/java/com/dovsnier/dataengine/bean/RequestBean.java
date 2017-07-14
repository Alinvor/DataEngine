package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/14.
 */
@Table(name = "request_info")
public class RequestBean extends BaseBean {

    @Column(name = "id", isId = true, autoGen = true)
    protected int id;
    @Column(name = "url")
    protected String url;
    @Column(name = "protocol")
    protected String protocol;
    @Column(name = "code")
    protected int code;
    @Column(name = "message")
    protected String message;
    @Column(name = "sentRequestAtMillis")
    protected long sentRequestAtMillis;
    @Column(name = "receivedResponseAtMillis")
    protected long receivedResponseAtMillis;
    @Column(name = "foreign")
    protected String foreign;
    @Column(name = "remark")
    protected String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSentRequestAtMillis() {
        return sentRequestAtMillis;
    }

    public void setSentRequestAtMillis(long sentRequestAtMillis) {
        this.sentRequestAtMillis = sentRequestAtMillis;
    }

    public long getReceivedResponseAtMillis() {
        return receivedResponseAtMillis;
    }

    public void setReceivedResponseAtMillis(long receivedResponseAtMillis) {
        this.receivedResponseAtMillis = receivedResponseAtMillis;
    }

    public String getForeign() {
        return foreign;
    }

    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
