package com.dovsnier.dataengine.bean;

import com.dvsnier.bean.BaseBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by lizw on 2017/7/17.
 */
@Deprecated
@Table(name = "meta_info")
public class MetaBean extends BaseBean {

    @Column(name = "id", isId = true, autoGen = true)
    protected int id;
    @Column(name = "identifier")
    protected String identifier;
    @Column(name = "foreign")
    protected String foreign;
    @Column(name = "position")
    protected long position;
    @Column(name = "index")
    protected long index;
    @Column(name = "isDelete")
    protected boolean isDelete;
    @Column(name = "invalid")
    protected boolean invalid;
    @Column(name = "timestamp")
    protected long timestamp;
}
