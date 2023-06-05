package com.carefrees.webview;

import androidx.core.content.FileProvider;

//<!-- android:name="com.tencent.smtt.utils.FileProvider" -->
/**
 * Providing a custom {@code FileProvider} prevents manifest {@code <provider>} name collisions.
 * <p>
 * See https://developer.android.com/guide/topics/manifest/provider-element.html for details.
 */
public class RNCCarefreesWebViewFileProvider extends FileProvider {

  // This class intentionally left blank.

}