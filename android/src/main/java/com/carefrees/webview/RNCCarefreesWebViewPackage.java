package com.carefrees.webview;

import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RNCCarefreesWebViewPackage extends TurboReactPackage {

  public void initTBS(ReactApplicationContext reactContext) {
      // 在调用TBS初始化、创建WebView之前进行如下配置
      HashMap map = new HashMap();
      map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
      map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
      QbSdk.initTbsSettings(map);
      QbSdk.setDownloadWithoutWifi(true);
      QbSdk.setNeedInitX5FirstTime(true);
      QbSdk.setTbsListener(
        new TbsListener() {
          @Override
          public void onDownloadFinish(int i) {
            //下载结束时的状态，下载成功时errorCode为100,其他均为失败，外部不需要关注具体的失败原因
            Log.d("QbSdk", "onDownloadFinish -->下载X5内核完成：" + i);
          }

          @Override
          public void onInstallFinish(int i) {
            //安装结束时的状态，安装成功时errorCode为200,其他均为失败，外部不需要关注具体的失败原因
            Log.d("QbSdk", "onInstallFinish -->安装X5内核进度：" + i);
          }

          @Override
          public void onDownloadProgress(int i) {
            //下载过程的通知，提供当前下载进度[0-100]
            Log.d("QbSdk", "onDownloadProgress -->下载X5内核进度：" + i);
          }
        });

      QbSdk.PreInitCallback cb =
        new QbSdk.PreInitCallback() {
          @Override
          public void onViewInitFinished(boolean arg0) {
            // x5內核初始化完成的回调，true表x5内核加载成功，否则表加载失败，会自动切换到系统内核。
            Log.d("QbSdk", " 内核加载 " + arg0);
          }

          @Override
          public void onCoreInitFinished() {
            //内核初始化完毕
            Log.d("QbSdk", "内核初始化完毕");
          }
        };
      // x5内核初始化接口
      QbSdk.initX5Environment(reactContext, cb);
  //    Log.i("QbSdk", "是否可以加载X5内核: " + QbSdk.canLoadX5(view.getContext()));
      Log.i("QbSdk", "app是否主动禁用了X5内核: " + QbSdk.getIsSysWebViewForcedByOuter());
    }

  @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        initTBS(reactContext);
        List<ViewManager> viewManagers = new ArrayList<>();
        viewManagers.add(new RNCCarefreesWebViewManager());
        return viewManagers;
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return () -> {
            final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
            boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
            moduleInfos.put(
                    RNCCarefreesWebViewModuleImpl.NAME,
                    new ReactModuleInfo(
                            RNCCarefreesWebViewModuleImpl.NAME,
                            RNCCarefreesWebViewModuleImpl.NAME,
                            false, // canOverrideExistingModule
                            false, // needsEagerInit
                            true, // hasConstants
                            false, // isCxxModule
                            isTurboModule // isTurboModule
                    ));
            return moduleInfos;
        };
    }

    @Nullable
    @Override
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        if (name.equals(RNCCarefreesWebViewModuleImpl.NAME)) {
            return new RNCCarefreesWebViewModule(reactContext);
        } else {
            return null;
        }
    }

}