package com.example.webviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView WebView1 = (WebView) findViewById(R.id.WebView1);
        WebView1.setWebViewClient(new WebViewClient());
        //WebView1.loadUrl("http://31.183.0.134:8000");
        WebSettings webSettings = WebView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setAppCacheMaxSize( 100 * 1024 * 1024 );
        webSettings.setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        webSettings.setAllowFileAccess( true );
        webSettings.setAppCacheEnabled( true );
        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default

        if ( !isNetworkAvailable()) { // loading offline
            Toast.makeText(MainActivity.this, "Nie można połączyć z serwerem. Zostanie wyświetlona najbardziej aktualna wersja, pobrana na urządzeniu.", Toast.LENGTH_LONG).show();
            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }
        WebView1.loadUrl( "http://192.168.1.196:8000" );


    }

}
