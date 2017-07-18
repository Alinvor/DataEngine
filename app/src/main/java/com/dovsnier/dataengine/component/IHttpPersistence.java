package com.dovsnier.dataengine.component;

import com.dovsnier.dataengine.bean.BodyBean;
import com.dovsnier.dataengine.bean.CookieBean;
import com.dovsnier.dataengine.bean.HeaderBean;

/**
 * Created by lizw on 2017/7/18.
 */

public interface IHttpPersistence extends IProtocalPersistence {

    void saveHeader(HeaderBean bean);

    void saveCookie(CookieBean bean);

    void saveBody(BodyBean bean);


}
