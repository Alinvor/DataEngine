package com.dovsnier.dataengine.adapter;

import com.dovsnier.dataengine.bean.AttributeBean;

/**
 * Created by lizw on 2017/7/18.
 */

public interface DocumentAdapter extends NodeAdapter {

    void saveAttribute(AttributeBean bean);

}
