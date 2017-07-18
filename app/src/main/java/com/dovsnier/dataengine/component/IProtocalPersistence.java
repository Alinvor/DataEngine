package com.dovsnier.dataengine.component;

import com.dovsnier.dataengine.bean.RequestBean;

/**
 * Created by lizw on 2017/7/18.
 */

public interface IProtocalPersistence extends IBasePersistence {

    void saveRequest(RequestBean bean);
}
