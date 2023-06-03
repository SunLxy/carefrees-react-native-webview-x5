package com.carefrees.webview;

import android.app.DownloadManager;
import android.net.Uri;
import android.webkit.ValueCallback;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RNCCarefreesWebViewModuleImpl.NAME)
public class RNCCarefreesWebViewModule extends NativeRNCCarefreesWebViewSpec {
    final private RNCCarefreesWebViewModuleImpl mRNCCarefreesWebViewModuleImpl;

    public RNCCarefreesWebViewModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mRNCCarefreesWebViewModuleImpl = new RNCCarefreesWebViewModuleImpl(reactContext);
    }

    @Override
    public void isFileUploadSupported(final Promise promise) {
        promise.resolve(mRNCCarefreesWebViewModuleImpl.isFileUploadSupported());
    }

    @Override
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