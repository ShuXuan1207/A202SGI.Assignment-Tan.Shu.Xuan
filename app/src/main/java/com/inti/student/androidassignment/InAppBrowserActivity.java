package com.inti.student.androidassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InAppBrowserActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_app_browser);

        webView = (WebView) findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        //This statement is used to enable the execution of JavaScript.

        webView.setVerticalScrollBarEnabled(false);
        //This statement hides the Vertical scroll bar and does not remove it.

        webView.setHorizontalScrollBarEnabled(false);
        //This statement hides the Horizontal scroll bar and does not remove it.

        webView.loadUrl(url);


    }

}
