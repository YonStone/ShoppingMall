package com.youdu.shoppingmall.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.network.http.HttpConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    private static final String WEB_URL = "web_url";
    private static final String IS_BANNER = "is_banner";
    @Bind(R.id.wb_info)
    WebView wbInfo;
    private String url;
    private boolean isBanner;

    public static Intent actionView(Context context, String url, Boolean isBanner) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEB_URL, url);
        intent.putExtra(IS_BANNER, isBanner);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra(WEB_URL);
        isBanner = getIntent().getBooleanExtra(IS_BANNER, false);
        setWebView(url);
    }

    private void setWebView(String url) {
        if (url != null) {
            wbInfo.loadUrl(isBanner ? HttpConstants.BASE_URL_JSON + url :
                    HttpConstants.BASE_URl_IMAGE + url);
            //覆盖 WebView 默认使用第三方或系统默认浏览器打开网页的行为,使网页用 WebView 打开
            wbInfo.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return true;
                }
            });
            // 启用支持 javascript
            WebSettings settings = wbInfo.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            // 优先使用缓存
            wbInfo.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
}
