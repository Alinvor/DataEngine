package com.dovsnier.dataengine.widget;

import okhttp3.Response;

/**
 * Created by DovSnier on 2017/7/18.
 */

public interface IHttpParse extends IParse {

    void parseHttp(Response response);
}
