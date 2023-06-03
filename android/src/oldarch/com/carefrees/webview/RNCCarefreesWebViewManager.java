package com.carefrees.webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.views.scroll.ScrollEventType;
import com.carefrees.webview.events.TopHttpErrorEvent;
import com.carefrees.webview.events.TopLoadingErrorEvent;
import com.carefrees.webview.events.TopLoadingFinishEvent;
import com.carefrees.webview.events.TopLoadingProgressEvent;
import com.carefrees.webview.events.TopLoadingStartEvent;
import com.carefrees.webview.events.TopMessageEvent;
import com.carefrees.webview.events.TopRenderProcessGoneEvent;
import com.carefrees.webview.events.TopShouldStartLoadWithRequestEvent;

import android.graphics.Color;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

public class RNCCarefreesWebViewManager extends SimpleViewManager<RNCCarefreesWebView> {

    private final RNCCarefreesWebViewManagerImpl mRNCCarefreesWebViewManagerImpl;

    public RNCCarefreesWebViewManager() {
        mRNCCarefreesWebViewManagerImpl = new RNCCarefreesWebViewManagerImpl();
    }

    @Override
    public String getName() {
        return RNCCarefreesWebViewManagerImpl.NAME;
    }

    @Override
    public RNCCarefreesWebView createViewInstance(ThemedReactContext context) {
        return mRNCCarefreesWebViewManagerImpl.createViewInstance(context);
    }

    public RNCCarefreesWebView createViewInstance(ThemedReactContext context, RNCCarefreesWebView webView) {
      return mRNCCarefreesWebViewManagerImpl.createViewInstance(context, webView);
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowFileAccess(view, value);
    }

    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowFileAccessFromFileURLs(view, value);

    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowUniversalAccessFromFileURLs(view, value);
    }

    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowsFullscreenVideo(view, value);
    }

    @ReactProp(name = "allowsProtectedMedia")
    public void setAllowsProtectedMedia(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowsProtectedMedia(view, value);
    }

    @ReactProp(name = "androidLayerType")
    public void setAndroidLayerType(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setAndroidLayerType(view, value);
    }

    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setApplicationNameForUserAgent(view, value);
    }

    @ReactProp(name = "basicAuthCredential")
    public void setBasicAuthCredential(RNCCarefreesWebView view, @Nullable ReadableMap value) {
        mRNCCarefreesWebViewManagerImpl.setBasicAuthCredential(view, value);
    }

    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setCacheEnabled(view, value);
    }

    @ReactProp(name = "cacheMode")
    public void setCacheMode(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setCacheMode(view, value);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setDomStorageEnabled(view, value);
    }

    @ReactProp(name = "downloadingMessage")
    public void setDownloadingMessage(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setDownloadingMessage(value);
    }

    @ReactProp(name = "forceDarkOn")
    public void setForceDarkOn(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setForceDarkOn(view, value);
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setGeolocationEnabled(view, value);
    }

    @ReactProp(name = "hasOnScroll")
    public void setHasOnScroll(RNCCarefreesWebView view, boolean hasScrollEvent) {
        mRNCCarefreesWebViewManagerImpl.setHasOnScroll(view, hasScrollEvent);
    }

    @ReactProp(name = "incognito")
    public void setIncognito(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setIncognito(view, value);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScript(view, value);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoaded(view, value);
    }

    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScriptForMainFrameOnly(view, value);

    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(view, value);

    }

    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setJavaScriptCanOpenWindowsAutomatically(view, value);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(RNCCarefreesWebView view, boolean enabled) {
        mRNCCarefreesWebViewManagerImpl.setJavaScriptEnabled(view, enabled);
    }

    @ReactProp(name = "lackPermissionToDownloadMessage")
    public void setLackPermissionToDownloadMessage(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setLackPermissionToDownloadMessage(value);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setMediaPlaybackRequiresUserAction(view, value);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setMessagingEnabled(view, value);
    }

    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setMessagingModuleName(view, value);
    }

    @ReactProp(name = "minimumFontSize")
    public void setMinimumFontSize(RNCCarefreesWebView view, int value) {
        mRNCCarefreesWebViewManagerImpl.setMinimumFontSize(view, value);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setMixedContentMode(view, value);
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setNestedScrollEnabled(view, value);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setOverScrollMode(view, value);
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSaveFormDataDisabled(view, value);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setScalesPageToFit(view, value);
    }

    @ReactProp(name = "setBuiltInZoomControls")
    public void setSetBuiltInZoomControls(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSetBuiltInZoomControls(view, value);
    }

    @ReactProp(name = "setDisplayZoomControls")
    public void setSetDisplayZoomControls(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSetDisplayZoomControls(view, value);
    }

    @ReactProp(name = "setSupportMultipleWindows")
    public void setSetSupportMultipleWindows(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSetSupportMultipleWindows(view, value);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setShowsHorizontalScrollIndicator(view, value);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setShowsVerticalScrollIndicator(view, value);
    }

    @ReactProp(name = "source")
    public void setSource(RNCCarefreesWebView view, @Nullable ReadableMap value) {
        mRNCCarefreesWebViewManagerImpl.setSource(view, value, false);
    }

    @ReactProp(name = "textZoom")
    public void setTextZoom(RNCCarefreesWebView view, int value) {
        mRNCCarefreesWebViewManagerImpl.setTextZoom(view, value);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setThirdPartyCookiesEnabled(view, value);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setUserAgent(view, value);
    }

    @Override
    protected void addEventEmitters(@NonNull ThemedReactContext reactContext, RNCCarefreesWebView view) {
        // Do not register default touch emitter and let WebView implementation handle touches
        view.setWebViewClient(new RNCCarefreesWebViewClient());
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> export = super.getExportedCustomDirectEventTypeConstants();
        if (export == null) {
            export = MapBuilder.newHashMap();
        }
        // Default events but adding them here explicitly for clarity
        export.put(TopLoadingStartEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingStart"));
        export.put(TopLoadingFinishEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingFinish"));
        export.put(TopLoadingErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingError"));
        export.put(TopMessageEvent.EVENT_NAME, MapBuilder.of("registrationName", "onMessage"));
        // !Default events but adding them here explicitly for clarity

        export.put(TopLoadingProgressEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingProgress"));
        export.put(TopShouldStartLoadWithRequestEvent.EVENT_NAME, MapBuilder.of("registrationName", "onShouldStartLoadWithRequest"));
        export.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll"));
        export.put(TopHttpErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onHttpError"));
        export.put(TopRenderProcessGoneEvent.EVENT_NAME, MapBuilder.of("registrationName", "onRenderProcessGone"));
        return export;
    }

    @Override
    public @Nullable
    Map<String, Integer> getCommandsMap() {
        return mRNCCarefreesWebViewManagerImpl.getCommandsMap();
    }

    @Override
    public void receiveCommand(@NonNull RNCCarefreesWebView reactWebView, String commandId, @Nullable ReadableArray args) {
        mRNCCarefreesWebViewManagerImpl.receiveCommand(reactWebView, commandId, args);
        super.receiveCommand(reactWebView, commandId, args);
    }

    @Override
    public void onDropViewInstance(@NonNull RNCCarefreesWebView view) {
        mRNCCarefreesWebViewManagerImpl.onDropViewInstance(view);
        super.onDropViewInstance(view);
    }
}