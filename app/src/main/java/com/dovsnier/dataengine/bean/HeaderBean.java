package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Date;

/**
 * Created by lizw on 2017/7/14.
 */
@Table(name = "header_info")
public class HeaderBean extends BaseBean {

    @Column(name = "id", isId = true, autoGen = false)
    protected String id;
    @Column(name = "date")
    protected Date date;
    @Column(name = "contentType")
    protected String contentType;
    @Column(name = "cookie")
    protected String cookie;
    @Column(name = "cookieId")
    protected String cookieId;
    @Column(name = "xPoweredBy")
    protected String xPoweredBy;
    @Column(name = "vary")
    protected String vary;
    @Column(name = "server")
    protected String server;
    @Column(name = "cfRay")
    protected String cfRay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getxPoweredBy() {
        return xPoweredBy;
    }

    public void setxPoweredBy(String xPoweredBy) {
        this.xPoweredBy = xPoweredBy;
    }

    public String getVary() {
        return vary;
    }

    public void setVary(String vary) {
        this.vary = vary;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getCfRay() {
        return cfRay;
    }

    public void setCfRay(String cfRay) {
        this.cfRay = cfRay;
    }
}
