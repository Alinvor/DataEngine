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
public class SplashActivity extends BaseActivity {

    @BindView(R.id.btn)
    TextView btn;
    private Unbinder unbinder;
    String url = "http://www.baidu.com";
    //    String url = "http://cl.chie.pw/index.php";
    protected String value;
    protected boolean isDebug = true;
    protected int strategy;
    protected HttpParse httpParse;
    protected HtmlParse htmlParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        strategy = 1;
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
            case 1:
                methodOfAsset();
                break;
            case 2:
                methodOfHttp();
                break;
        }
    }

    protected void methodOfAsset() {
        if (null == htmlParse) {
            htmlParse = new HtmlParse();
            htmlParse.setDebug(isDebug);
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
                    try {
                        value = response.body().string();
                        runOnUiThread(value);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        enqueue1024();
    }
}
