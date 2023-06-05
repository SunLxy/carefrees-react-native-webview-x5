import React from 'react';
import { Text, View } from 'react-native';
import { IOSWebViewProps, AndroidWebViewProps, WindowsWebViewProps } from './WebViewTypes';

export type WebViewProps = IOSWebViewProps & AndroidWebViewProps & WindowsWebViewProps;

// This "dummy" WebView is to render something for unsupported platforms,
// like for example Expo SDK "web" platform.
const WebView: React.FunctionComponent<WebViewProps> = () => (
	<View style={{ alignSelf: 'flex-start' }}>
		<Text style={{ color: 'red' }}>
			React Native WebView does not support this platform.
		</Text>
	</View>
);


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

export { WebView };
export default WebView;
