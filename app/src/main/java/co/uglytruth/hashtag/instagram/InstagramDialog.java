package co.uglytruth.hashtag.instagram;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.WebView;

public class InstagramDialog extends Dialog {

    private WebView webView;

    public InstagramDialog(@NonNull Context context) {
        super(context);

       setWebView(context);
    }

    private void setWebView(Context context){

        webView = new WebView(context);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new InstagramWebClient());
    }

    public InstagramDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setWebView(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
