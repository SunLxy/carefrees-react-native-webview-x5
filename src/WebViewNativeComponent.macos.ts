import { requireNativeComponent } from "react-native";
import type { NativeWebViewMacOS } from "./WebViewTypes";

const RNCCarefreesWebView: typeof NativeWebViewMacOS = requireNativeComponent(
  'RNCCarefreesWebView',
);

export default RNCCarefreesWebView;
