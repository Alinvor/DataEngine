package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/14.
 */
@Table(name = "cookie_info")
public class CookieBean extends BaseBean {

    @Column(name = "id", isId = true, autoGen = false)
    protected String id;
    @Column(name = "uid")
    protected String uid;
    @Column(name = "expires")
    protected String expires;
    @Column(name = "path")
    protected String path;
    @Column(name = "domain")
    protected String domain;
    @Column(name = "remarks")
    protected String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
