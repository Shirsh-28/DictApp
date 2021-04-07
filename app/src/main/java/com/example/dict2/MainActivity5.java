package com.example.dict2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity5 extends AppCompatActivity {
    private WebView webView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        webView3 = findViewById(R.id.webView3);
        webView3.setWebViewClient(new WebViewClient());
        webView3.loadUrl("https://www.merriam-webster.com");

    }
    @Override
    public void onBackPressed(){
        if(webView3.canGoBack()){
            webView3.goBack();
        }
        else{
            super.onBackPressed();}
    }
}