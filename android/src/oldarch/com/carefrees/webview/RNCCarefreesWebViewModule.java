package com.carefrees.webview;

import android.app.DownloadManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.ValueCallback;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.HashMap;

@ReactModule(name = RNCCarefreesWebViewModuleImpl.NAME)
public class RNCCarefreesWebViewModule extends ReactContextBaseJavaModule {
    final private RNCCarefreesWebViewModuleImpl mRNCCarefreesWebViewModuleImpl;

  public RNCCarefreesWebViewModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mRNCCarefreesWebViewModuleImpl = new RNCCarefreesWebViewModuleImpl(reactContext);
    }

    @ReactMethod
    public void isFileUploadSupported(final Promise promise) {
        promise.resolve(mRNCCarefreesWebViewModuleImpl.isFileUploadSupported());
    }

    @ReactMethod
    public void shouldStartLoadWithLockIdentifier(boolean shouldStart, double lockIdentifier) {
        mRNCCarefreesWebViewModuleImpl.shouldStartLoadWithLockIdentifier(shouldStart, lockIdentifier);
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> filePathCallback, String acceptType) {
        mRNCCarefreesWebViewModuleImpl.startPhotoPickerIntent(acceptType, filePathCallback);
    }

    public boolean startPhotoPickerIntent(final ValueCallback<Uri[]> callback, final String[] acceptTypes, final boolean allowMultiple) {
        return mRNCCarefreesWebViewModuleImpl.startPhotoPickerIntent(acceptTypes, allowMultiple, callback);
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        mRNCCarefreesWebViewModuleImpl.setDownloadRequest(request);
    }

    public void downloadFile(String downloadingMessage) {
        mRNCCarefreesWebViewModuleImpl.downloadFile(downloadingMessage);
    }

    public boolean grantFileDownloaderPermissions(String downloadingMessage, String lackPermissionToDownloadMessage) {
        return mRNCCarefreesWebViewModuleImpl.grantFileDownloaderPermissions(downloadingMessage, lackPermissionToDownloadMessage);
    }

    @NonNull
    @Override
    public String getName() {
        return RNCCarefreesWebViewModuleImpl.NAME;
    }
}