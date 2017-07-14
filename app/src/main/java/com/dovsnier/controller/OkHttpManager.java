package com.dovsnier.controller;

import com.dvsnier.bean.BaseBean;
import com.dvsnier.utils.JsonUtils;
import com.dvsnier.widget.LifeCycle;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by lizw on 2017/7/14.
 */

public class OkHttpManager implements LifeCycle {

    protected static final String TAG = OkHttpManager.class.getSimpleName();
    protected static final OkHttpManager instance = new OkHttpManager();
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient okHttpClient;

    private OkHttpManager() {
        if (null == okHttpClient)
            okHttpClient = new OkHttpClient();
    }

    public static OkHttpManager getInstance() {
        return instance;
    }


    public void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void post(String url, BaseBean baseBean, Callback callback) {
        RequestBody requestBody = RequestBody.create(JSON, JsonUtils.obj2Json(baseBean));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public void onDestroy() {
        if (null != okHttpClient)
            okHttpClient = null;
    }
}
