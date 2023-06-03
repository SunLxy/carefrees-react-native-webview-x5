package com.carefrees.webview;

import com.tencent.smtt.sdk.WebView;

/**
 * Implement this interface in order to config your {@link WebView}. An instance of that
 * implementation will have to be given as a constructor argument to {@link RNCCarefreesWebViewManager}.
 */
public interface RNCCarefreesWebViewConfig {
    void configWebView(WebView webView);
}