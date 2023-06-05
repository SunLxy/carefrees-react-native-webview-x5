import React, { forwardRef, useCallback, useImperativeHandle, useRef } from 'react';
import {
  Image,
  View,
  ImageSourcePropType,
  HostComponent,
} from 'react-native';
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
  IOSWebViewProps,
  DecelerationRateConstant,
  WebViewSourceUri,
} from './WebViewTypes';

import styles from './WebView.styles';



const { resolveAssetSource } = Image;
const processDecelerationRate = (
  decelerationRate: DecelerationRateConstant | number | undefined,
) => {
  let newDecelerationRate = decelerationRate;
  if (newDecelerationRate === 'normal') {
    newDecelerationRate = 0.998;
  } else if (newDecelerationRate === 'fast') {
    newDecelerationRate = 0.99;
  }
  return newDecelerationRate;
};

const useWarnIfChanges = <T extends unknown>(value: T, name: string) => {
  const ref = useRef(value);
  if (ref.current !== value) {
    console.warn(`Changes to property ${name} do nothing after the initial render.`);
    ref.current = value;
  }
}

const WebViewComponent = forwardRef<{}, IOSWebViewProps>(({
  javaScriptEnabled = true,
  cacheEnabled = true,
  originWhitelist = defaultOriginWhitelist,
  useSharedProcessPool = true,
  textInteractionEnabled = true,
  injectedJavaScript,
  injectedJavaScriptBeforeContentLoaded,
  injectedJavaScriptForMainFrameOnly = true,
  injectedJavaScriptBeforeContentLoadedForMainFrameOnly = true,
  startInLoadingState,
  onNavigationStateChange,
  onLoadStart,
  onError,
  onLoad,
  onLoadEnd,
  onLoadProgress,
  onContentProcessDidTerminate: onContentProcessDidTerminateProp,
  onFileDownload,
  onHttpError: onHttpErrorProp,
  onMessage: onMessageProp,
  renderLoading,
  renderError,
  style,
  containerStyle,
  source,
  nativeConfig,
  allowsInlineMediaPlayback,
  allowsAirPlayForMediaPlayback,
  mediaPlaybackRequiresUserAction,
  dataDetectorTypes,
  incognito,
  decelerationRate: decelerationRateProp,
  onShouldStartLoadWithRequest: onShouldStartLoadWithRequestProp,
  ...otherProps
}, ref) => {
  const webViewRef = useRef<React.ComponentRef<HostComponent<NativeProps>> | null>(null);

  const onShouldStartLoadWithRequestCallback = useCallback((
    shouldStart: boolean,
    _url: string,
    lockIdentifier = 0,
  ) => {
    RNCCarefreesWebViewModule.shouldStartLoadWithLockIdentifier(shouldStart, lockIdentifier);
  }, []);

  const { onLoadingStart, onShouldStartLoadWithRequest, onMessage, viewState, setViewState, lastErrorEvent, onHttpError, onLoadingError, onLoadingFinish, onLoadingProgress, onContentProcessDidTerminate } = useWebViewLogic({
    onNavigationStateChange,
    onLoad,
    onError,
    onHttpErrorProp,
    onLoadEnd,
    onLoadProgress,
    onLoadStart,
    onMessageProp,
    startInLoadingState,
    originWhitelist,
    onShouldStartLoadWithRequestProp,
    onShouldStartLoadWithRequestCallback,
    onContentProcessDidTerminateProp,
  });

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
  }), [setViewState, webViewRef]);


  useWarnIfChanges(allowsInlineMediaPlayback, 'allowsInlineMediaPlayback');
  useWarnIfChanges(allowsAirPlayForMediaPlayback, 'allowsAirPlayForMediaPlayback');
  useWarnIfChanges(incognito, 'incognito');
  useWarnIfChanges(mediaPlaybackRequiresUserAction, 'mediaPlaybackRequiresUserAction');
  useWarnIfChanges(dataDetectorTypes, 'dataDetectorTypes');

  let otherView = null;
  if (viewState === 'LOADING') {
    otherView = (renderLoading || defaultRenderLoading)();
  } else if (viewState === 'ERROR') {
    invariant(lastErrorEvent != null, 'lastErrorEvent expected to be non-null');
    otherView = (renderError || defaultRenderError)(
      lastErrorEvent?.domain,
      lastErrorEvent?.code ?? 0,
      lastErrorEvent?.description ?? '',
    );
  } else if (viewState !== 'IDLE') {
    console.error(`RNCCarefreesWebView invalid state encountered: ${viewState}`);
  }

  const webViewStyles = [styles.container, styles.webView, style];
  const webViewContainerStyle = [styles.container, containerStyle];

  const decelerationRate = processDecelerationRate(decelerationRateProp);

  const NativeWebView
    = (nativeConfig?.component as typeof RNCCarefreesWebView | undefined)
    || RNCCarefreesWebView;

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

  const webView = (
    <NativeWebView
      key="webViewKey"
      {...otherProps}
      javaScriptEnabled={javaScriptEnabled}
      cacheEnabled={cacheEnabled}
      useSharedProcessPool={useSharedProcessPool}
      textInteractionEnabled={textInteractionEnabled}
      decelerationRate={decelerationRate}
      messagingEnabled={typeof onMessageProp === 'function'}
      messagingModuleName="" // android ONLY
      onLoadingError={onLoadingError}
      onLoadingFinish={onLoadingFinish}
      onLoadingProgress={onLoadingProgress}
      onFileDownload={onFileDownload}
      onLoadingStart={onLoadingStart}
      onHttpError={onHttpError}
      onMessage={onMessage}
      onShouldStartLoadWithRequest={onShouldStartLoadWithRequest}
      onContentProcessDidTerminate={onContentProcessDidTerminate}
      injectedJavaScript={injectedJavaScript}
      injectedJavaScriptBeforeContentLoaded={injectedJavaScriptBeforeContentLoaded}
      injectedJavaScriptForMainFrameOnly={injectedJavaScriptForMainFrameOnly}
      injectedJavaScriptBeforeContentLoadedForMainFrameOnly={injectedJavaScriptBeforeContentLoadedForMainFrameOnly}
      dataDetectorTypes={!dataDetectorTypes || Array.isArray(dataDetectorTypes) ? dataDetectorTypes : [dataDetectorTypes]}
      allowsAirPlayForMediaPlayback={allowsAirPlayForMediaPlayback}
      allowsInlineMediaPlayback={allowsInlineMediaPlayback}
      incognito={incognito}
      mediaPlaybackRequiresUserAction={mediaPlaybackRequiresUserAction}
      newSource={newSource}
      style={webViewStyles}
      hasOnFileDownload={!!onFileDownload}
      ref={webViewRef}
      // @ts-expect-error old arch only
      source={sourceResolved}
      {...nativeConfig?.props}
    />
  );

  return (
    <View style={webViewContainerStyle}>
      {webView}
      {otherView}
    </View>
  );
})

// no native implementation for iOS, depends only on permissions
const isFileUploadSupported: () => Promise<boolean>
  = async () => true;

const WebView = Object.assign(WebViewComponent, { isFileUploadSupported });

/**
 * 获取初始x5内核加载信息
*/
export const getInitX5sdkInfo = () => {
  return {}
}


interface ItemType<T, P> {
  /**
   * 类型
   * */
  type?: T;
  /**
   * errorCode 或进度条
   * */
  errorCode?: P;
  /**
   * 类型文字描述
  */
  msg?: string
}

export type InitTBSReturn =
  ItemType<'downloadFinish', number> |
  ItemType<'installFinish', number> |
  ItemType<'downloadProgress', number> |
  ItemType<'viewInitFinished', boolean> |
  ItemType<'coreInitFinished', boolean>

/**

/**
 * 下载x5内核
*/
export const initTBS = (listener?: (info: InitTBSReturn) => void) => {
  if (listener) {
    listener({})
  }
}

export default WebView;
