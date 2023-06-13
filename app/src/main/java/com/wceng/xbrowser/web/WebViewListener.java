package com.wceng.xbrowser.web;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

public interface WebViewListener {
    /**
     * 页面开始加载时调用，显示加载进度条等操作
     * @param view WebView 实例
     * @param url 当前页面 URL
     * @param favicon 当前页面网站图标
     */
    void onPageStarted(WebView view, String url, Bitmap favicon);

    /**
     * 页面加载完成时调用，隐藏加载进度条等操作
     * @param view WebView 实例
     * @param url 当前页面 URL
     */
    void onPageFinished(WebView view, String url);

    /**
     * 当页面加载出错时调用，例如网络错误、404 等
     * @param view WebView 实例
     * @param request 出错的 Web 资源请求
     * @param error 出错的详细信息，包括错误码和错误描述
     */
    void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);

    /**
     * 当 SSL 认证失败时调用，可以在此处让用户选择是否继续访问
     * @param view WebView 实例
     * @param handler 处理 SSL 认证的 Handler，可以通过它处理 SSL 认证
     * @param error 认证失败的错误信息
     */
    void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error);

    /**
     * 应用程序决定是否加载某个 URL，可以用来实现 WebView 中的拦截功能
     * @param view WebView 实例
     * @param url 要加载的 URL
     * @return 是否拦截该请求，true 表示拦截，false 表示不拦截
     */
    boolean shouldOverrideUrlLoading(WebView view, String url);

    /**
     * 当页面加载进度发生改变时调用，可以用来更新加载进度条等操作
     * @param view WebView 实例
     * @param newProgress 新的加载进度值，范围 0-100
     */
    void onProgressChanged(WebView view, int newProgress);

    /**
     * 当 Web 页面标题发生变化时调用
     * @param view WebView 实例
     * @param title 新的页面标题
     */
    void onReceivedTitle(WebView view, String title);

    /**
     * 当 WebView 加载的网站包含图标资源时调用
     * @param view WebView 实例
     * @param icon 网站图标
     */
    void onReceivedIcon(WebView view, Bitmap icon);

    /**
     * 处理 HTML5 地理位置信息定位请求，当网站需要获取用户的地理位置信息时调用此方法。
     * @param origin 地理位置信息源
     * @param callback 回调给网页进行选择
     */
    void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback);

    /**
     * 处理 Web 页面的权限请求，例如定位权限、存储权限等。
     * @param request 权限请求实例
     */
    void onPermissionRequest(PermissionRequest request);

    /**
     * 处理 JavaScript 弹出框警告框，例如 alert、confirm 等。
     * @param view WebView 实例
     * @param url 当前页面 URL
     * @param message 弹框显示的消息内容
     * @param result 弹框回调实例，可以通过它对弹框进行操作
     * @return 是否处理该弹框，true 表示已处理，false 表示未处理。
     */
    boolean onJsAlert(WebView view, String url, String message, JsResult result);

    /**
     * 处理 JavaScript 确认框，例如 confirm 等。
     * @param view WebView 实例
     * @param url 当前页面 URL
     * @param message 确认框显示的消息内容
     * @param result 确认框回调实例，可以通过它对确认框进行操作
     * @return 是否处理该确认框，true 表示已处理，false 表示未处理。
     */
    boolean onJsConfirm(WebView view, String url, String message, JsResult result);

    /**
     * 处理 JavaScript 输入框，例如 prompt 等。
     * @param view WebView 实例
     * @param url 当前页面 URL
     * @param message 输入框显示的消息内容
     * @param defaultValue 输入框默认值
     * @param result 输入框回调实例，可以通过它对输入框进行操作
     * @return 是否处理该输入框，true 表示已处理，false 表示未处理。
     */
    boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result);

    /**
     * 处理 HTML5 视频全屏播放，当页面中播放了 HTML5 视频时调用此方法。
     * @param view WebView 实例
     * @param callback 全屏播放回调实例，可以通过它对全屏播放进行操作
     */
    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

    /**
     * 隐藏 HTML5 全屏视频播放。
     */
    void onHideCustomView();

    /**
     * 取消权限请求
     * @param request 权限请求实例
     */
    void onPermissionRequestCanceled(PermissionRequest request);

    /**
     * 当 WebView 加载的网站包含触摸图标资源时调用
     * @param view WebView 实例
     * @param url 触摸图标 URL
     * @param precomposed 是否有预合成效果
     */
    void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed);

    /**
     * 处理文件选择请求
     * @param webView WebView 实例
     * @param filePathCallback 文件路径回调实例，可以通过它获取选择的文件路径
     * @param fileChooserParams 文件选择参数
     * @return 是否处理该文件选择请求，true 表示已处理，false 表示未处理。
     */
    boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams);

    /**
     * 隐藏 HTML5 地理位置信息定位请求。
     */
    void onGeolocationPermissionsHidePrompt();

    /**
     * 当 WebView 加载出错时调用
     * @param view WebView 实例
     * @param request 出错的 Web 资源请求
     * @param errorResponse 出错的 Web 资源响应，包括错误码和错误描述
     */
    void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse);

    /**
     * 处理客户端证书请求
     * @param view WebView 实例
     * @param request 客户端证书请求实例
     */
    void onReceivedClientCertRequest(WebView view, ClientCertRequest request);

    /**
     * 应用程序决定是否处理按键事件
     * @param view WebView 实例
     * @param event 按键事件
     * @return 是否处理该按键事件，true 表示已处理，false 表示未处理。
     */
    boolean shouldOverrideKeyEvent(WebView view, KeyEvent event);
}
