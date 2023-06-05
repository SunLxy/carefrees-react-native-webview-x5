import React, { forwardRef, ReactElement, useCallback, useEffect, useImperativeHandle, useMemo, useRef } from 'react';

import {
  Image,
  View,
  ImageSourcePropType,
  HostComponent,
  NativeModules,
  NativeEventEmitter
} from 'react-native';

import BatchedBridge from 'react-native/Libraries/BatchedBridge/BatchedBridge';

import invariant from 'invariant';

import RNCCarefreesWebView, { Commands, NativeProps } from "./RNCCarefreesWebViewNativeComponent";
import RNCCarefreesWebViewModule from "./NativeRNCCarefreesWebView";
import {
  defaultOriginWhitelist,
  defaultRenderError,
  defaultRenderLoading,
  useWebViewLogic,
} from './WebViewShared';
import {
  AndroidWebViewProps, WebViewSourceUri,
} from './WebViewTypes';

import styles from './WebView.styles';

const { resolveAssetSource } = Image;

/**
 * A simple counter to uniquely identify WebView instances. Do not use this for anything else.
 */
let uniqueRef = 0;

const WebViewComponent = forwardRef<{}, AndroidWebViewProps>(({
  overScrollMode = 'always',
  javaScriptEnabled = true,
  thirdPartyCookiesEnabled = true,
  scalesPageToFit = true,
  allowsFullscreenVideo = false,
  allowFileAccess = false,
  saveFormDataDisabled = false,
  cacheEnabled = true,
  androidLayerType = "none",
  originWhitelist = defaultOriginWhitelist,
  setSupportMultipleWindows = true,
  setBuiltInZoomControls = true,
  setDisplayZoomControls = false,
  nestedScrollEnabled = false,
  startInLoadingState,
  onNavigationStateChange,
  onLoadStart,
  onError,
  onLoad,
  onLoadEnd,
  onLoadProgress,
  onHttpError: onHttpErrorProp,
  onRenderProcessGone: onRenderProcessGoneProp,
  onMessage: onMessageProp,
  renderLoading,
  renderError,
  style,
  containerStyle,
  source,
  nativeConfig,
  onShouldStartLoadWithRequest: onShouldStartLoadWithRequestProp,
  ...otherProps
}, ref) => {
  const messagingModuleName = useRef<string>(`WebViewMessageHandler${uniqueRef += 1}`).current;
  const webViewRef = useRef<React.ComponentRef<HostComponent<NativeProps>> | null>(null);

  const onShouldStartLoadWithRequestCallback = useCallback((shouldStart: boolean,
    url: string,
    lockIdentifier?: number) => {
    if (lockIdentifier) {
      RNCCarefreesWebViewModule.shouldStartLoadWithLockIdentifier(shouldStart, lockIdentifier);
    } else if (shouldStart && webViewRef.current) {
      Commands.loadUrl(webViewRef.current, url);
    }
  }, []);

  const { onLoadingStart, onShouldStartLoadWithRequest, onMessage, viewState, setViewState, lastErrorEvent, onHttpError, onLoadingError, onLoadingFinish, onLoadingProgress, onRenderProcessGone } = useWebViewLogic({
    onNavigationStateChange,
    onLoad,
    onError,
    onHttpErrorProp,
    onLoadEnd,
    onLoadProgress,
    onLoadStart,
    onRenderProcessGoneProp,
    onMessageProp,
    startInLoadingState,
    originWhitelist,
    onShouldStartLoadWithRequestProp,
    onShouldStartLoadWithRequestCallback,
  })

  useImperativeHandle(ref, () => ({
    goForward: () => webViewRef.current && Commands.goForward(webViewRef.current),
    goBack: () => webViewRef.current && Commands.goBack(webViewRef.current),
    reload: () => {
      setViewState(
        'LOADING',
      );
      if (webViewRef.current) {
        Commands.reload(webViewRef.current)
      }
    },
    stopLoading: () => webViewRef.current && Commands.stopLoading(webViewRef.current),
    postMessage: (data: string) => webViewRef.current && Commands.postMessage(webViewRef.current, data),
    injectJavaScript: (data: string) => webViewRef.current && Commands.injectJavaScript(webViewRef.current, data),
    requestFocus: () => webViewRef.current && Commands.requestFocus(webViewRef.current),
    clearFormData: () => webViewRef.current && Commands.clearFormData(webViewRef.current),
    clearCache: (includeDiskFiles: boolean) => webViewRef.current && Commands.clearCache(webViewRef.current, includeDiskFiles),
    clearHistory: () => webViewRef.current && Commands.clearHistory(webViewRef.current),
  }), [setViewState, webViewRef]);

  const directEventCallbacks = useMemo(() => ({
    onShouldStartLoadWithRequest,
    onMessage,
  }), [onMessage, onShouldStartLoadWithRequest]);

  useEffect(() => {
    BatchedBridge.registerCallableModule(messagingModuleName, directEventCallbacks);
  }, [messagingModuleName, directEventCallbacks])

  let otherView: ReactElement | undefined;
  if (viewState === 'LOADING') {
    otherView = (renderLoading || defaultRenderLoading)();
  } else if (viewState === 'ERROR') {
    invariant(lastErrorEvent != null, 'lastErrorEvent expected to be non-null');
    if (lastErrorEvent) {
      otherView = (renderError || defaultRenderError)(
        lastErrorEvent.domain,
        lastErrorEvent.code,
        lastErrorEvent.description,
      );
    }
  } else if (viewState !== 'IDLE') {
    console.error(`RNCCarefreesWebView invalid state encountered: ${viewState}`);
  }

  const webViewStyles = [styles.container, styles.webView, style];
  const webViewContainerStyle = [styles.container, containerStyle];

  if (typeof source !== "number" && source && 'method' in source) {
    if (source.method === 'POST' && source.headers) {
      console.warn(
        'WebView: `source.headers` is not supported when using POST.',
      );
    } else if (source.method === 'GET' && source.body) {
      console.warn('WebView: `source.body` is not supported when using GET.');
    }
  }

  const NativeWebView
    = (nativeConfig?.component as (typeof RNCCarefreesWebView | undefined)) || RNCCarefreesWebView;

  const sourceResolved = resolveAssetSource(source as ImageSourcePropType)
  const newSource = typeof sourceResolved === "object" ? Object.entries(sourceResolved as WebViewSourceUri).reduce((prev, [currKey, currValue]) => {
    return {
      ...prev,
      [currKey]: currKey === "headers" && currValue && typeof currValue === "object" ? Object.entries(currValue).map(
        ([key, value]) => {
          return {
            name: key,
            value
          }
        }) : currValue
    }
  }, {}) : sourceResolved

  const webView = <NativeWebView
    key="webViewKey"
    {...otherProps}
    messagingEnabled={typeof onMessageProp === 'function'}
    messagingModuleName={messagingModuleName}

    hasOnScroll={!!otherProps.onScroll}
    onLoadingError={onLoadingError}
    onLoadingFinish={onLoadingFinish}
    onLoadingProgress={onLoadingProgress}
    onLoadingStart={onLoadingStart}
    onHttpError={onHttpError}
    onRenderProcessGone={onRenderProcessGone}
    onMessage={onMessage}
    onShouldStartLoadWithRequest={onShouldStartLoadWithRequest}
    ref={webViewRef}
    // TODO: find a better way to type this.
    // @ts-expect-error source is old arch
    source={sourceResolved}
    newSource={newSource}
    style={webViewStyles}
    overScrollMode={overScrollMode}
    javaScriptEnabled={javaScriptEnabled}
    thirdPartyCookiesEnabled={thirdPartyCookiesEnabled}
    scalesPageToFit={scalesPageToFit}
    allowsFullscreenVideo={allowsFullscreenVideo}
    allowFileAccess={allowFileAccess}
    saveFormDataDisabled={saveFormDataDisabled}
    cacheEnabled={cacheEnabled}
    androidLayerType={androidLayerType}
    setSupportMultipleWindows={setSupportMultipleWindows}
    setBuiltInZoomControls={setBuiltInZoomControls}
    setDisplayZoomControls={setDisplayZoomControls}
    nestedScrollEnabled={nestedScrollEnabled}
    {...nativeConfig?.props}
  />

  return (
    <View style={webViewContainerStyle}>
      {webView}
      {otherView}
    </View>
  );
});

// native implementation should return "true" only for Android 5+
const { isFileUploadSupported } = RNCCarefreesWebViewModule;

const WebView = Object.assign(WebViewComponent, { isFileUploadSupported });

const eventEmitter = new NativeEventEmitter(NativeModules.RNCCarefreesWebView);

export interface InitX5sdkInfoReturn {
  isX5DownloadFinish: number,// 下载X5内核完成
  isX5InstallFinish: number,// 安装X5内核进度
  isX5DownloadProgress: number,// 下载过程的通知，提供当前下载进度[0-100]
  isX5ViewInitFinished: boolean,// x5內核初始化完成的回调，true表x5内核加载成功，否则表加载失败，会自动切换到系统内核。
  isX5CoreInitFinished: boolean,// 内核初始化完毕
  isDisableX5: boolean,// 是否禁用 x5内核
}


interface ItemType<T, P> {
  /**
   * 类型
   * */
  type: T;
  /**
   * errorCode 或进度条
   * */
  errorCode: P;
  /**
   * 类型文字描述
  */
  msg: string
}

export type InitTBSReturn =
  ItemType<'downloadFinish', number> |
  ItemType<'installFinish', number> |
  ItemType<'downloadProgress', number> |
  ItemType<'viewInitFinished', boolean> |
  ItemType<'coreInitFinished', boolean>

/**
 * 获取初始x5内核加载信息
*/
export const getInitX5sdkInfo = (): InitX5sdkInfoReturn => {
  return NativeModules.RNCCarefreesWebView.getInitX5sdkInfo();
}

/**
 * 下载x5内核
*/
export const initTBS = (listener?: (info: InitTBSReturn) => void) => {
  return eventEmitter.addListener('RNCCarefreesWebViewX5', (info) => {
    if (listener) {
      listener(info)
    }
  });
}

export default WebView;
