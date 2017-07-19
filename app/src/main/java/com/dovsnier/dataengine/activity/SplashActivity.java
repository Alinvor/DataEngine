package com.dovsnier.dataengine.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dovsnier.controller.HtmlParse;
import com.dovsnier.controller.HttpParse;
import com.dovsnier.controller.OkHttpManager;
import com.dovsnier.dataengine.R;
import com.dovsnier.dataengine.component.UiThreadListener;
import com.dovsnier.utils.MD5;
import com.dvsnier.base.BaseActivity;
import com.dvsnier.cache.CacheManager;
import com.dvsnier.utils.D;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author DovSnier
 * @version 0.0.1
 * @since 1.7
 */
public class SplashActivity extends BaseActivity implements UiThreadListener {

    @BindView(R.id.btn)
    TextView btn;
    private Unbinder unbinder;
    protected String value;
    protected boolean isDebug = true;
    protected int strategy;
    protected HttpParse httpParse;
    protected HtmlParse htmlParse;
    protected double random;

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
        if (null != value) value = null;
        if (null != httpParse) httpParse.onDestroy();
        if (null != htmlParse) htmlParse.onDestroy();
    }

    protected void enqueue1024() {
        String msg = "请求中...";
        showProgressDialog(msg);
        runOnUiThread(msg);

        switch (strategy) {
            case 0:
                methodOfAsset();
                break;
            case 1:
                methodOfHttp();
                break;
        }
    }

    protected void methodOfAsset() {
        if (null == htmlParse) {
            htmlParse = new HtmlParse();
            htmlParse.setDebug(isDebug);
            htmlParse.setUiThreadListener(this);
        }

        value = D.hook(this, "http", "test.html");
        dismissProgressDialog();

        runOnUiThread(value);
        htmlParse();
    }

    protected void htmlParse() {
        htmlParse.setValue(value);
        htmlParse.htmlParse();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SplashActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void methodOfHttp() {
        if (null == httpParse) {
            httpParse = new HttpParse();
            httpParse.setDebug(isDebug);
            httpParse.setUiThreadListener(this);
        }
        String url;

        double radomUrl = Math.random() * 100;
        if (radomUrl > 50.0d) {
            url = "http://www.baidu.com";
        } else {
            url = "http://cl.chie.pw/index.php";
        }

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
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            httpParse(response);
                        }
                    }).start();
                }
            }
        });
    }

    protected void httpParse(Response response) {
        httpParse.parseHttp(response);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SplashActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void runOnUiThread(final String value) {
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
            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        random = Math.random() * 100;
        if (random > 50.0d) {
            strategy = 0;
        } else {
            strategy = 1;
        }
        enqueue1024();
    }
}
