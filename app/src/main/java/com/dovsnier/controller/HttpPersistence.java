package com.dovsnier.controller;

import com.dovsnier.dataengine.bean.BodyBean;
import com.dovsnier.dataengine.bean.CookieBean;
import com.dovsnier.dataengine.bean.HeaderBean;
import com.dovsnier.dataengine.bean.RequestBean;
import com.dovsnier.dataengine.component.IHttpPersistence;

/**
 * Created by DovSnier on 2017/7/18.
 */
public class HttpPersistence extends Persistence implements IHttpPersistence {

    @Override
    public void saveRequest(RequestBean bean) {
        saveOrUpdate(bean);
    }

    @Override
    public void saveHeader(HeaderBean bean) {
        saveOrUpdate(bean);
    }

    @Override
    public void saveCookie(CookieBean bean) {
        saveOrUpdate(bean);
    }

    @Override
    public void saveBody(BodyBean bean) {
        saveOrUpdate(bean);
    }
}
