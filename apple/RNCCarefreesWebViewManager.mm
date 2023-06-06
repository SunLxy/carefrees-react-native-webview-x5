#import <React/RCTUIManager.h>

#import "RNCCarefreesWebViewManager.h"
#import "RNCCarefreesWebViewImpl.h"
#import "RNCCarefreesWebViewDecisionManager.h"
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNCWebViewSpec/RNCWebViewSpec.h"
#endif

#if TARGET_OS_OSX
#define RNCView NSView
@class NSView;
#else
#define RNCView UIView
@class UIView;
#endif  // TARGET_OS_OSX

@implementation RCTConvert (WKWebView)
#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 130000 /* iOS 13 */
RCT_ENUM_CONVERTER(WKContentMode, (@{
  @"recommended": @(WKContentModeRecommended),
  @"mobile": @(WKContentModeMobile),
  @"desktop": @(WKContentModeDesktop),
}), WKContentModeRecommended, integerValue)
#endif

#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 150000 /* iOS 15 */
RCT_ENUM_CONVERTER(RNCWebViewPermissionGrantType, (@{
  @"grantIfSameHostElsePrompt": @(RNCWebViewPermissionGrantType_GrantIfSameHost_ElsePrompt),
  @"grantIfSameHostElseDeny": @(RNCWebViewPermissionGrantType_GrantIfSameHost_ElseDeny),
  @"deny": @(RNCWebViewPermissionGrantType_Deny),
  @"grant": @(RNCWebViewPermissionGrantType_Grant),
  @"prompt": @(RNCWebViewPermissionGrantType_Prompt),
}), RNCWebViewPermissionGrantType_Prompt, integerValue)
#endif
@end


@implementation RNCCarefreesWebViewManager {
    NSConditionLock *_shouldStartLoadLock;
    BOOL _shouldStartLoad;
}

RCT_EXPORT_MODULE(RNCarefreesWebView)

- (RNCView *)view
{
  return [[RNCCarefreesWebViewImpl alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(source, NSDictionary)
// New arch only
RCT_CUSTOM_VIEW_PROPERTY(newSource, NSDictionary, RNCCarefreesWebViewImpl) {}
RCT_EXPORT_VIEW_PROPERTY(onFileDownload, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onLoadingStart, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onLoadingFinish, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onLoadingError, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onLoadingProgress, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onHttpError, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onShouldStartLoadWithRequest, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onContentProcessDidTerminate, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(injectedJavaScript, NSString)
RCT_EXPORT_VIEW_PROPERTY(injectedJavaScriptBeforeContentLoaded, NSString)
RCT_EXPORT_VIEW_PROPERTY(injectedJavaScriptForMainFrameOnly, BOOL)
RCT_EXPORT_VIEW_PROPERTY(injectedJavaScriptBeforeContentLoadedForMainFrameOnly, BOOL)
RCT_EXPORT_VIEW_PROPERTY(javaScriptEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(javaScriptCanOpenWindowsAutomatically, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowFileAccessFromFileURLs, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowUniversalAccessFromFileURLs, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowsInlineMediaPlayback, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowsAirPlayForMediaPlayback, BOOL)
RCT_EXPORT_VIEW_PROPERTY(mediaPlaybackRequiresUserAction, BOOL)
RCT_EXPORT_VIEW_PROPERTY(dataDetectorTypes, WKDataDetectorTypes)
RCT_EXPORT_VIEW_PROPERTY(contentInset, UIEdgeInsets)
RCT_EXPORT_VIEW_PROPERTY(automaticallyAdjustContentInsets, BOOL)
RCT_EXPORT_VIEW_PROPERTY(autoManageStatusBarEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(hideKeyboardAccessoryView, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowsBackForwardNavigationGestures, BOOL)
RCT_EXPORT_VIEW_PROPERTY(incognito, BOOL)
RCT_EXPORT_VIEW_PROPERTY(pagingEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(applicationNameForUserAgent, NSString)
RCT_EXPORT_VIEW_PROPERTY(cacheEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowsLinkPreview, BOOL)
RCT_EXPORT_VIEW_PROPERTY(allowingReadAccessToURL, NSString)
RCT_EXPORT_VIEW_PROPERTY(basicAuthCredential, NSDictionary)

#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 110000 /* __IPHONE_11_0 */
RCT_EXPORT_VIEW_PROPERTY(contentInsetAdjustmentBehavior, UIScrollViewContentInsetAdjustmentBehavior)
#endif
#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 130000 /* __IPHONE_13_0 */
RCT_EXPORT_VIEW_PROPERTY(automaticallyAdjustsScrollIndicatorInsets, BOOL)
#endif

#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 130000 /* iOS 13 */
RCT_EXPORT_VIEW_PROPERTY(contentMode, WKContentMode)
#endif

#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 140000 /* iOS 14 */
RCT_EXPORT_VIEW_PROPERTY(limitsNavigationsToAppBoundDomains, BOOL)
#endif

#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 140500 /* iOS 14.5 */
RCT_EXPORT_VIEW_PROPERTY(textInteractionEnabled, BOOL)
#endif

#if defined(__IPHONE_OS_VERSION_MAX_ALLOWED) && __IPHONE_OS_VERSION_MAX_ALLOWED >= 150000 /* iOS 15 */
RCT_EXPORT_VIEW_PROPERTY(mediaCapturePermissionGrantType, RNCWebViewPermissionGrantType)
#endif

/**
 * Expose methods to enable messaging the webview.
 */
RCT_EXPORT_VIEW_PROPERTY(messagingEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(onMessage, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onScroll, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(enableApplePay, BOOL)
RCT_EXPORT_VIEW_PROPERTY(menuItems, NSArray);
// New arch only
RCT_CUSTOM_VIEW_PROPERTY(hasOnFileDownload, BOOL, RNCCarefreesWebViewImpl) {}
RCT_EXPORT_VIEW_PROPERTY(onCustomMenuSelection, RCTDirectEventBlock)
RCT_CUSTOM_VIEW_PROPERTY(pullToRefreshEnabled, BOOL, RNCCarefreesWebViewImpl) {
  view.pullToRefreshEnabled = json == nil ? false : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(bounces, BOOL, RNCCarefreesWebViewImpl) {
  view.bounces = json == nil ? true : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(useSharedProcessPool, BOOL, RNCCarefreesWebViewImpl) {
  view.useSharedProcessPool = json == nil ? true : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(userAgent, NSString, RNCCarefreesWebViewImpl) {
  view.userAgent = [RCTConvert NSString: json];
}

RCT_CUSTOM_VIEW_PROPERTY(scrollEnabled, BOOL, RNCCarefreesWebViewImpl) {
  view.scrollEnabled = json == nil ? true : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(sharedCookiesEnabled, BOOL, RNCCarefreesWebViewImpl) {
  view.sharedCookiesEnabled = json == nil ? false : [RCTConvert BOOL: json];
}

#if !TARGET_OS_OSX
RCT_CUSTOM_VIEW_PROPERTY(decelerationRate, CGFloat, RNCCarefreesWebViewImpl) {
  view.decelerationRate = json == nil ? UIScrollViewDecelerationRateNormal : [RCTConvert CGFloat: json];
}
#endif // !TARGET_OS_OSX

RCT_CUSTOM_VIEW_PROPERTY(directionalLockEnabled, BOOL, RNCCarefreesWebViewImpl) {
  view.directionalLockEnabled = json == nil ? true : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(showsHorizontalScrollIndicator, BOOL, RNCCarefreesWebViewImpl) {
  view.showsHorizontalScrollIndicator = json == nil ? true : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(showsVerticalScrollIndicator, BOOL, RNCCarefreesWebViewImpl) {
  view.showsVerticalScrollIndicator = json == nil ? true : [RCTConvert BOOL: json];
}

RCT_CUSTOM_VIEW_PROPERTY(keyboardDisplayRequiresUserAction, BOOL, RNCCarefreesWebViewImpl) {
  view.keyboardDisplayRequiresUserAction = json == nil ? true : [RCTConvert BOOL: json];
}

#if !TARGET_OS_OSX
    #define BASE_VIEW_PER_OS() UIView
#else
    #define BASE_VIEW_PER_OS() NSView
#endif

#define QUICK_RCT_EXPORT_COMMAND_METHOD(name)                                                                                           \
RCT_EXPORT_METHOD(name:(nonnull NSNumber *)reactTag)                                                                                    \
{                                                                                                                                       \
[self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BASE_VIEW_PER_OS() *> *viewRegistry) {   \
    RNCCarefreesWebViewImpl *view = (RNCCarefreesWebViewImpl *)viewRegistry[reactTag];                                                                    \
    if (![view isKindOfClass:[RNCCarefreesWebViewImpl class]]) {                                                                                 \
      RCTLogError(@"Invalid view returned from registry, expecting RNCarefreesWebView, got: %@", view);                                         \
    } else {                                                                                                                            \
      [view name];                                                                                                                      \
    }                                                                                                                                   \
  }];                                                                                                                                   \
}
#define QUICK_RCT_EXPORT_COMMAND_METHOD_PARAMS(name, in_param, out_param)                                                               \
RCT_EXPORT_METHOD(name:(nonnull NSNumber *)reactTag in_param)                                                                           \
{                                                                                                                                       \
[self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BASE_VIEW_PER_OS() *> *viewRegistry) {   \
    RNCCarefreesWebViewImpl *view = (RNCCarefreesWebViewImpl *)viewRegistry[reactTag];                                                                    \
    if (![view isKindOfClass:[RNCCarefreesWebViewImpl class]]) {                                                                                 \
      RCTLogError(@"Invalid view returned from registry, expecting RNCarefreesWebView, got: %@", view);                                         \
    } else {                                                                                                                            \
      [view name:out_param];                                                                                                            \
    }                                                                                                                                   \
  }];                                                                                                                                   \
}

QUICK_RCT_EXPORT_COMMAND_METHOD(reload)
QUICK_RCT_EXPORT_COMMAND_METHOD(goBack)
QUICK_RCT_EXPORT_COMMAND_METHOD(goForward)
QUICK_RCT_EXPORT_COMMAND_METHOD(stopLoading)
QUICK_RCT_EXPORT_COMMAND_METHOD(requestFocus)

QUICK_RCT_EXPORT_COMMAND_METHOD_PARAMS(postMessage, message:(NSString *)message, message)
QUICK_RCT_EXPORT_COMMAND_METHOD_PARAMS(injectJavaScript, script:(NSString *)script, script)

RCT_EXPORT_METHOD(shouldStartLoadWithLockIdentifier:(BOOL)shouldStart
                                        lockIdentifier:(double)lockIdentifier)
{
    [[RNCCarefreesWebViewDecisionManager getInstance] setResult:shouldStart forLockIdentifier:(int)lockIdentifier];
}

// Thanks to this guard, we won't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRNCWebViewSpecJSI>(params);
}
#endif

@end
