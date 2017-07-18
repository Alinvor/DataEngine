package com.dovsnier.dataengine.adapter;

import com.dovsnier.dataengine.bean.BodyBean;
import com.dovsnier.dataengine.bean.HeadBean;

/**
 * Created by lizw on 2017/7/18.
 */

public interface NodeAdapter extends MateAdapter {

    void saveHead(HeadBean bean);

    void saveBody(BodyBean bean);
}
