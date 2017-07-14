package com.dovsnier.dataengine.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dovsnier.controller.OkHttpManager;
import com.dovsnier.dataengine.R;
import com.dovsnier.utils.MD5;
import com.dvsnier.base.BaseActivity;
import com.dvsnier.cache.CacheManager;

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
    }

    protected void enqueue1024() {
        showProgressDialog();
//        String url = "https://www.baidu.com";
        String url = "https://cl.chie.pw/index.php";
//        OkHttpManager.getInstance().post(url, new BaseBean(), new Callback() {
        OkHttpManager.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissProgressDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissProgressDialog();
                if (null != response) {
                    final String value = response.body().string();
                    runOnUiThread(value);
                }
            }
        });
    }

    protected void runOnUiThread(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
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
}
