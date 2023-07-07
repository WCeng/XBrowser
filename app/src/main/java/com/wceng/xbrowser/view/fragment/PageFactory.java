package com.wceng.xbrowser.view.fragment;

/**
 * 当前窗口的Page生成工厂
 */
public interface PageFactory {
    HomePage createHomePage();

    WebPage createWebPage();

    SearchPage createSearchPage();

    HomePage getSingleHomePage();

    SearchPage getSingleSearchPage();

    WebPage getSingleWebPage();

    void destroy();
}
