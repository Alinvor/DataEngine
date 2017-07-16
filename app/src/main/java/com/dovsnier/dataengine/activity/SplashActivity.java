package com.dovsnier.dataengine.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dovsnier.controller.EngineManager;
import com.dovsnier.controller.OkHttpManager;
import com.dovsnier.dataengine.R;
import com.dovsnier.dataengine.bean.CookieBean;
import com.dovsnier.dataengine.bean.HeaderBean;
import com.dovsnier.dataengine.bean.RequestBean;
import com.dovsnier.utils.MD5;
import com.dvsnier.base.BaseActivity;
import com.dvsnier.cache.CacheManager;
import com.dvsnier.utils.D;
import com.dvsnier.utils.LogUtil;
import com.dvsnier.utils.StringUtils;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author DovSnier
 * @version 0.0.1
 * @since 1.7
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.btn)
    TextView btn;
    private Unbinder unbinder;
    protected final String charset = "UTF-8";
    String url = "http://www.baidu.com";
    //    String url = "http://cl.chie.pw/index.php";
    protected String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (null != value)
            value = null;
    }

    protected void enqueue1024() {
        String msg = "请求中...";
        showProgressDialog(msg);
        runOnUiThread(msg);

//        methodOfHttp();

        methodOfAsset();
    }

    protected void methodOfAsset() {
        value = D.hook(this, "http", "test.html");
        dismissProgressDialog();

        runOnUiThread(value);
        htmlParse();
    }

    protected void methodOfHttp() {
        //        OkHttpManager.getInstance().post(url, new BaseBean(), new Callback() {
        OkHttpManager.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                dismissProgressDialog();
                runOnUiThread(e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) {
                dismissProgressDialog();
                if (null != response) {
                    try {
                        value = response.body().string();
                        runOnUiThread(value);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            saveOrUpdate(response);
                        }
                    }).start();
                }
            }
        });
    }

    protected void saveOrUpdate(Response response) {
        //                    protected String id;
//                    protected String url;
//                    protected String protocol;
//                    protected int code;
//                    protected String message;
//                    protected long sentRequestAtMillis;
//                    protected long receivedResponseAtMillis;
//                    protected String foreign;
//                    protected String remark;

        String url = response.request().url().toString();
        RequestBean requestBean = new RequestBean(); //TODO Request information
        requestBean.setUrl(url);
        requestBean.setProtocol(response.protocol().name());
        requestBean.setCode(response.code());
        requestBean.setMessage(response.message());
        requestBean.setSentRequestAtMillis(response.sentRequestAtMillis());
        requestBean.setReceivedResponseAtMillis(response.receivedResponseAtMillis());
        final String foreign = MD5.obtainValue(url, String.valueOf(System.currentTimeMillis()));
        requestBean.setForeign(foreign);
        if (StringUtils.isNotEmpty(value)) {
//            requestBean.setRemark(String.format("%s", String.valueOf(value.length() / 1024.0f), "kb"));
            requestBean.setRemark(String.format(Locale.CHINA, "%s", value));
            htmlParse();
        }

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
                    }
                } else {
                    cookieBean.setRemarks(split[i]);
                }
            }
        }

        try {
            EngineManager.getInstance().getDbManager().saveOrUpdate(requestBean);
            EngineManager.getInstance().getDbManager().saveOrUpdate(headerBean);
            EngineManager.getInstance().getDbManager().saveOrUpdate(cookieBean);
        } catch (DbException e) {
            e.printStackTrace();
            runOnUiThread(e.getMessage());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SplashActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void runOnUiThread(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn.setGravity(Gravity.LEFT | Gravity.TOP);
                btn.setText(value);
                CacheManager.getInstance().put(MD5.obtainDefaultValue(), value).commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        enqueue1024();
    }

    protected void htmlParse() {
        try {
            String valueUTF_8 = new String(value.getBytes(charset));
            Parser parser = Parser.createParser(valueUTF_8, charset);
            LogUtil.w(TAG, parser.getEncoding());
            NodeList nodeList = parser.parse(null);
            handleNode(nodeList);
        } catch (ParserException e) {
            e.printStackTrace();
            runOnUiThread(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            runOnUiThread(e.getMessage());
        }
    }

    protected void handleNode(NodeList nodeList) {
        if (null == nodeList) return;
        SimpleNodeIterator simpleNodeIterator = nodeList.elements();
        if (null != simpleNodeIterator) {
            while (simpleNodeIterator.hasMoreNodes()) {
                Node node = simpleNodeIterator.nextNode();
                NodeList nodeChildren = node.getChildren();
                if (null == nodeChildren) {
                    LogUtil.d(TAG, "    ");
                    LogUtil.d(TAG, String.format("->%s", node.getClass().getSimpleName()));
//                    LogUtil.d(TAG, node.toPlainTextString());
//                    LogUtil.d(TAG, node.getText());
                    LogUtil.d(TAG, node.toHtml());
                    if (node instanceof Tag) {
                        parseTag((Tag) node);
                    } else if (node instanceof Text) {
                        parseText((Text) node);
                    } else if (node instanceof Remark) {
                        parseRemark((Remark) node);
                    } else {
                        Toast.makeText(this, "notice", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    handleNode(nodeChildren);
                }
            }
        }
    }

    protected void parseRemark(Remark node) {
        Remark remark = node;
        String remarkText = remark.getText();
    }

    protected void parseText(Text node) {
        Text text = node;
        String textText = text.getText();
        if (text instanceof TextNode) {
            TextNode textNode = (TextNode) text;
            String textNodeText = textNode.getText();
            NodeList textNodeChildren = textNode.getChildren();
            Node textNodeParent = textNode.getParent();
            if (null != textNodeParent) {
                String textNodeParentText = textNodeParent.getText();
                if (textNodeParent instanceof Tag) {
                    parseTag((Tag) textNodeParent);
                }
            }
        }
    }

    protected void parseTag(Tag node) {
        Tag tag = node;
        String tagName = tag.getTagName();
        String rawTagName = tag.getRawTagName();
        Vector vector = tag.getAttributesEx();
        if (tag instanceof TagNode) {
            TagNode tagNode = (TagNode) tag;
            String tagNodeTagName = tagNode.getTagName();
            String tagNodeRawTagName = tagNode.getRawTagName();
            Vector tagNodeAttributesEx = tagNode.getAttributesEx();
        }
        StringBuilder sb = new StringBuilder();
        for (Object item : vector) {
            sb.append(item);
        }
        LogUtil.d(TAG, String.format("TAG: %s,%s,%s", tagName, rawTagName, sb.toString()));
    }
}
