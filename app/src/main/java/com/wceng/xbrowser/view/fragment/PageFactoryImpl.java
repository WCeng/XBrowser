package com.wceng.xbrowser.view.fragment;

import com.wceng.xbrowser.window.Window;

public class PageFactoryImpl implements PageFactory {

        HomePage homePage;
        SearchPage searchPage;
        WebPage webPage;

        Window window;

    public PageFactoryImpl(Window window) {
        this.window = window;
    }

    @Override
        public HomePage createHomePage() {
            return HomePage.newInstance(window);
        }

        @Override
        public WebPage createWebPage() {
            return WebPage.newInstance(window);
        }

        @Override
        public SearchPage createSearchPage() {
            return SearchPage.newInstance(window);
        }

        @Override
        public HomePage getSingleHomePage() {
            if (homePage == null) {
                homePage = createHomePage();
            }
            return homePage;
        }

        @Override
        public SearchPage getSingleSearchPage() {
            if (searchPage == null)
                searchPage = createSearchPage();
            return searchPage;
        }

        @Override
        public WebPage getSingleWebPage() {
            if (webPage == null)
                webPage = createWebPage();
            return webPage;
        }

    @Override
    public void destroy() {
        if(homePage != null){
            homePage = null;
        }
        if(searchPage != null){
            searchPage = null;
        }
        if(webPage != null){
            webPage = null;
        }
        window = null;
    }
}