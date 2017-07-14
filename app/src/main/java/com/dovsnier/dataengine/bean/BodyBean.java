package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/14.
 */
@Table(name = "body_info")
public class BodyBean extends BaseBean {

    @Column(name = "id", isId = true, autoGen = false)
    protected String id;
    @Column(name = "body")
    protected String body;
    @Column(name = "httpEquiv")
    protected String httpEquiv;
    @Column(name = "content")
    protected String content;
    @Column(name = "charset")
    protected String charset;

}
