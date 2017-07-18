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

}
