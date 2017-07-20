package com.dovsnier.controller;

import com.dovsnier.dataengine.adapter.HttpAdapter;
import com.dovsnier.dataengine.bean.BodyBean;
import com.dovsnier.dataengine.bean.CookieBean;
import com.dovsnier.dataengine.bean.HeaderBean;
import com.dovsnier.dataengine.bean.RequestBean;
import com.dovsnier.dataengine.widget.DocumentType;
import com.dovsnier.dataengine.widget.IHttpParse;
import com.dvsnier.utils.StringUtils;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by DovSnier on 2017/7/18.
 */
public class HttpParse extends Html implements IHttpParse {

    protected String value;
    protected String url;
    protected HttpAdapter adapter;

    public HttpParse() {
    }

    @Override
    public void parseHttp(Response response) {
//
//                    protected String id;
//                    protected String url;
//                    protected String protocol;
//                    protected int code;
//                    protected String message;
//                    protected long sentRequestAtMillis;
//                    protected long receivedResponseAtMillis;
//                    protected String foreign;
//                    protected String remark;

        if (null == response) {
            warning("the current response is null.");
            return;
        }
        adapter = new HttpAdapter();
        try {
            value = response.body().string();
            runOnUiThread(value);
        } catch (IOException e) {
            e.printStackTrace();
            error(TAG, e);
        }
        url = response.request().url().toString();
        RequestBean requestBean = new RequestBean(); //TODO Request information
        requestBean.setUrl(url);
        requestBean.setProtocol(response.protocol().name());
        requestBean.setCode(response.code());
        requestBean.setMessage(response.message());
        requestBean.setSentRequestAtMillis(response.sentRequestAtMillis());
        requestBean.setReceivedResponseAtMillis(response.receivedResponseAtMillis());
        final String foreign = adapter.generateConversationIdentifier();
        requestBean.setForeign(foreign);
//        if (StringUtils.isNotEmpty(value)) {
//            requestBean.setRemark(String.format("%s", String.valueOf(value.length() / 1024.0f), "kb"));
//            requestBean.setRemark(String.format(Locale.CHINA, "%s", value));
//        } else {
//            warning("the current value is null.");
//        }
        adapter.saveRequest(requestBean);

//
//                    protected String id;
//                    protected Date date;
//                    protected String contentType;
//                    protected String cookie;
//                    protected String cookieId;
//                    protected String xPoweredBy;
//                    protected String vary;
//                    protected String server;
//                    protected String cfRay;

        Headers headers = response.headers();
        HeaderBean headerBean = new HeaderBean(); //TODO Header information
        headerBean.setId(foreign);
        headerBean.setCookieId(foreign);
        headerBean.setDate(headers.getDate("date"));
        headerBean.setContentType(headers.get("content-type"));
        final String cookie = headers.get("set-cookie");
        headerBean.setCookie(cookie);
        headerBean.setxPoweredBy(headers.get("x-powered-by"));
        headerBean.setVary(headers.get("vary"));
        headerBean.setServer(headers.get("server"));
        headerBean.setCfRay(headers.get("cf-ray"));
        adapter.saveHeader(headerBean);

// __cfduid=d4fbf1c85de8e574f9672b68b81a5febe1500020017;
// expires=Sat, 14-Jul-18 08:13:37 GMT;
// path=/;
// domain=.chie.pw;
// HttpOnly

        CookieBean cookieBean = new CookieBean(); // TODO Cookie information
        cookieBean.setId(foreign);
        if (StringUtils.isNotEmpty(cookie)) {
            String[] split = cookie.split(";");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                if (split[i].contains("=")) {
                    String[] splitSub = split[i].split("=");
                    if (splitSub[0].contains("uid")) {
                        cookieBean.setUid(splitSub[1]);
                    } else if (splitSub[0].contains("domain")) {
                        cookieBean.setDomain(splitSub[1]);
                    } else if (splitSub[0].contains("expires")) {
                        cookieBean.setExpires(splitSub[1]);
                    } else if (splitSub[0].contains("path")) {
                        cookieBean.setPath(splitSub[1]);
                    } else {
                        // nothing to do
                        debug(split[i]);
                    }
                } else {
                    cookieBean.setRemarks(split[i]);
                }
            }
        }
        adapter.saveCookie(cookieBean);

        BodyBean bodyBean = new BodyBean(); // TODO Body information
        bodyBean.setIdentifier(foreign);
        bodyBean.setBody(adapter.generateIdentifier(foreign));
        bodyBean.setForeign(adapter.generateForeign(foreign));
        if (StringUtils.isNotEmpty(value)) {
            bodyBean.setContent(String.format(Locale.CHINA, "%s", value));
        } else {
            warning("the current value is null.");
        }
        bodyBean.setName("response_body");
        bodyBean.setType(DocumentType.DOCUMENT.name());
        bodyBean.setPosition(0L);
        bodyBean.setIndex(0L);
        adapter.saveBody(bodyBean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != value) value = null;
        if (null != url) url = null;
        if (null != adapter) adapter.onDestroy();
    }
}
