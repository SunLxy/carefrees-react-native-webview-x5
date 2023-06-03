package com.carefrees.webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNCCarefreesWebViewManagerDelegate;
import com.facebook.react.viewmanagers.RNCCarefreesWebViewManagerInterface;
import com.facebook.react.views.scroll.ScrollEventType;
import com.carefrees.webview.events.TopHttpErrorEvent;
import com.carefrees.webview.events.TopLoadingErrorEvent;
import com.carefrees.webview.events.TopLoadingFinishEvent;
import com.carefrees.webview.events.TopLoadingProgressEvent;
import com.carefrees.webview.events.TopLoadingStartEvent;
import com.carefrees.webview.events.TopMessageEvent;
import com.carefrees.webview.events.TopRenderProcessGoneEvent;
import com.carefrees.webview.events.TopShouldStartLoadWithRequestEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

@ReactModule(name = RNCCarefreesWebViewManagerImpl.NAME)
public class RNCCarefreesWebViewManager extends SimpleViewManager<RNCCarefreesWebView>
        implements RNCCarefreesWebViewManagerInterface<RNCCarefreesWebView> {

    private final ViewManagerDelegate<RNCCarefreesWebView> mDelegate;
    private final RNCCarefreesWebViewManagerImpl mRNCCarefreesWebViewManagerImpl;

    public RNCCarefreesWebViewManager() {
        mDelegate = new RNCCarefreesWebViewManagerDelegate<>(this);
        mRNCCarefreesWebViewManagerImpl = new RNCCarefreesWebViewManagerImpl();
    }

    @Nullable
    @Override
    protected ViewManagerDelegate<RNCCarefreesWebView> getDelegate() {
        return mDelegate;
    }

    @NonNull
    @Override
    public String getName() {
        return RNCCarefreesWebViewManagerImpl.NAME;
    }

    @NonNull
    @Override
    protected RNCCarefreesWebView createViewInstance(@NonNull ThemedReactContext context) {
        return mRNCCarefreesWebViewManagerImpl.createViewInstance(context);
    }

    @Override
    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowFileAccess(view, value);
    }

    @Override
    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowFileAccessFromFileURLs(view, value);

    }

    @Override
    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowUniversalAccessFromFileURLs(view, value);
    }

    @Override
    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowsFullscreenVideo(view, value);
    }

    @Override
    @ReactProp(name = "allowsProtectedMedia")
    public void setAllowsProtectedMedia(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setAllowsProtectedMedia(view, value);
    }

    @Override
    @ReactProp(name = "androidLayerType")
    public void setAndroidLayerType(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setAndroidLayerType(view, value);
    }

    @Override
    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setApplicationNameForUserAgent(view, value);
    }

    @Override
    @ReactProp(name = "basicAuthCredential")
    public void setBasicAuthCredential(RNCCarefreesWebView view, @Nullable ReadableMap value) {
        mRNCCarefreesWebViewManagerImpl.setBasicAuthCredential(view, value);
    }

    @Override
    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setCacheEnabled(view, value);
    }

    @Override
    @ReactProp(name = "cacheMode")
    public void setCacheMode(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setCacheMode(view, value);
    }

    @Override
    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setDomStorageEnabled(view, value);
    }

    @Override
    @ReactProp(name = "downloadingMessage")
    public void setDownloadingMessage(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setDownloadingMessage(value);
    }

    @Override
    @ReactProp(name = "forceDarkOn")
    public void setForceDarkOn(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setForceDarkOn(view, value);
    }

    @Override
    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setGeolocationEnabled(view, value);
    }

    @Override
    @ReactProp(name = "hasOnScroll")
    public void setHasOnScroll(RNCCarefreesWebView view, boolean hasScrollEvent) {
        mRNCCarefreesWebViewManagerImpl.setHasOnScroll(view, hasScrollEvent);
    }

    @Override
    @ReactProp(name = "incognito")
    public void setIncognito(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setIncognito(view, value);
    }

    @Override
    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScript(view, value);
    }

    @Override
    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoaded(view, value);
    }

    @Override
    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScriptForMainFrameOnly(view, value);

    }

    @Override
    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(view, value);

    }

    @Override
    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setJavaScriptCanOpenWindowsAutomatically(view, value);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(RNCCarefreesWebView view, boolean enabled) {
        mRNCCarefreesWebViewManagerImpl.setJavaScriptEnabled(view, enabled);
    }

    @Override
    @ReactProp(name = "lackPermissionToDownloadMessage")
    public void setLackPermissionToDownloadMessage(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setLackPermissionToDownloadMessage(value);
    }

    @Override
    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setMediaPlaybackRequiresUserAction(view, value);
    }

    @Override
    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setMessagingEnabled(view, value);
    }

    @Override
    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setMessagingModuleName(view, value);
    }

    @Override
    @ReactProp(name = "minimumFontSize")
    public void setMinimumFontSize(RNCCarefreesWebView view, int value) {
        mRNCCarefreesWebViewManagerImpl.setMinimumFontSize(view, value);
    }

    @Override
    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setMixedContentMode(view, value);
    }

    @Override
    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setNestedScrollEnabled(view, value);
    }

    @Override
    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setOverScrollMode(view, value);
    }

    @Override
    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSaveFormDataDisabled(view, value);
    }

    @Override
    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setScalesPageToFit(view, value);
    }

    @Override
    @ReactProp(name = "setBuiltInZoomControls")
    public void setSetBuiltInZoomControls(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSetBuiltInZoomControls(view, value);
    }

    @Override
    @ReactProp(name = "setDisplayZoomControls")
    public void setSetDisplayZoomControls(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSetDisplayZoomControls(view, value);
    }

    @Override
    @ReactProp(name = "setSupportMultipleWindows")
    public void setSetSupportMultipleWindows(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setSetSupportMultipleWindows(view, value);
    }

    @Override
    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setShowsHorizontalScrollIndicator(view, value);
    }

    @Override
    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setShowsVerticalScrollIndicator(view, value);
    }

    @Override
    @ReactProp(name = "newSource")
    public void setNewSource(RNCCarefreesWebView view, @Nullable ReadableMap value) {
        mRNCCarefreesWebViewManagerImpl.setSource(view, value, true);
    }

    @Override
    @ReactProp(name = "textZoom")
    public void setTextZoom(RNCCarefreesWebView view, int value) {
        mRNCCarefreesWebViewManagerImpl.setTextZoom(view, value);
    }

    @Override
    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(RNCCarefreesWebView view, boolean value) {
        mRNCCarefreesWebViewManagerImpl.setThirdPartyCookiesEnabled(view, value);
    }

    /* iOS PROPS - no implemented here */
    @Override
    public void setAllowingReadAccessToURL(RNCCarefreesWebView view, @Nullable String value) {}

    @Override
    public void setAllowsBackForwardNavigationGestures(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setAllowsInlineMediaPlayback(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setAllowsAirPlayForMediaPlayback(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setAllowsLinkPreview(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setAutomaticallyAdjustContentInsets(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setAutoManageStatusBarEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setBounces(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setContentInset(RNCCarefreesWebView view, @Nullable ReadableMap value) {}

    @Override
    public void setContentInsetAdjustmentBehavior(RNCCarefreesWebView view, @Nullable String value) {}

    @Override
    public void setContentMode(RNCCarefreesWebView view, @Nullable String value) {}

    @Override
    public void setDataDetectorTypes(RNCCarefreesWebView view, @Nullable ReadableArray value) {}

    @Override
    public void setDecelerationRate(RNCCarefreesWebView view, double value) {}

    @Override
    public void setDirectionalLockEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setEnableApplePay(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setHideKeyboardAccessoryView(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setKeyboardDisplayRequiresUserAction(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setPagingEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setPullToRefreshEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setScrollEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setSharedCookiesEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setUseSharedProcessPool(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setLimitsNavigationsToAppBoundDomains(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setTextInteractionEnabled(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setHasOnFileDownload(RNCCarefreesWebView view, boolean value) {}

    @Override
    public void setMenuItems(RNCCarefreesWebView view, ReadableArray value) {}

    @Override
    public void setMediaCapturePermissionGrantType(RNCCarefreesWebView view, @Nullable String value) {}
    /* !iOS PROPS - no implemented here */

    @Override
    @ReactProp(name = "userAgent")
    public void setUserAgent(RNCCarefreesWebView view, @Nullable String value) {
        mRNCCarefreesWebViewManagerImpl.setUserAgent(view, value);
    }

    // These will never be called because we use the shared impl for now
  @Override
  public void goBack(RNCCarefreesWebView view) {
    view.goBack();
  }

  @Override
  public void goForward(RNCCarefreesWebView view) {
    view.goForward();
  }

  @Override
  public void reload(RNCCarefreesWebView view) {
    view.reload();
  }

  @Override
  public void stopLoading(RNCCarefreesWebView view) {
    view.stopLoading();
  }

  @Override
  public void injectJavaScript(RNCCarefreesWebView view, String javascript) {
      view.evaluateJavascriptWithFallback(javascript);
  }

  @Override
  public void requestFocus(RNCCarefreesWebView view) {
      view.requestFocus();
  }

  @Override
  public void postMessage(RNCCarefreesWebView view, String data) {
      try {
        JSONObject eventInitDict = new JSONObject();
        eventInitDict.put("data", data);
        view.evaluateJavascriptWithFallback(
          "(function () {" +
            "var event;" +
            "var data = " + eventInitDict.toString() + ";" +
            "try {" +
            "event = new MessageEvent('message', data);" +
            "} catch (e) {" +
            "event = document.createEvent('MessageEvent');" +
            "event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);" +
            "}" +
            "document.dispatchEvent(event);" +
            "})();"
        );
      } catch (JSONException e) {
        throw  new RuntimeException(e);
      }
  }

  @Override
  public void loadUrl(RNCCarefreesWebView view, String url) {
      view.loadUrl(url);
  }

  @Override
  public void clearFormData(RNCCarefreesWebView view) {
      view.clearFormData();
  }

  @Override
  public void clearCache(RNCCarefreesWebView view, boolean includeDiskFiles) {
      view.clearCache(includeDiskFiles);
  }

  @Override
  public void clearHistory(RNCCarefreesWebView view) {
      view.clearHistory();
  }
  // !These will never be called

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