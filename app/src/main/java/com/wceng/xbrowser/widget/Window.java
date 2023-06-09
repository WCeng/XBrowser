package com.wceng.xbrowser.widget;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.wceng.xbrowser.view.fragment.HomePage;
import com.wceng.xbrowser.view.fragment.SearchPage;
import com.wceng.xbrowser.view.fragment.WebPage;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressLint("ViewConstructor")
public class Window extends PageContainer {

    private final WindowController mWindowController;
    private String mTitle;
    private IWindowListener mWindowListener;
    private final IPageFactory mPageFactory;

    interface IWindowListener {
        // 当窗口标题被更新时调用
        void onTitleUpdate(String title);

        // 当前页前进/后退状态发生变化时调用
        void onPageNavigationStateChanged(boolean canGoBack, boolean canGoForward);
    }

    public void setWindowListener(IWindowListener windowListener) {
        mWindowListener = windowListener;
    }

    public Window(@NotNull Context context) {
        super(context);
        mWindowController = new WindowController();
        mPageFactory = new PageFactory();
    }

    public IPageFactory getPageFactory() {
        return mPageFactory;
    }

    public WindowController getWindowController() {
        return mWindowController;
    }

    public boolean canGoBack() {
        return getAllPages().size() > 1;
    }


    /**
     * Window控制器
     */
    public class WindowController {
        public void enterHomePage() {
            List<Fragment> allPages = getAllPages();
            for (int i = 0; i < allPages.size(); i++) {
                Fragment page = allPages.get(i);
                if (page instanceof HomePage) {
                    showPage(page);
                } else {
                    removePage(page);
                }
            }
        }

        public void enterSearchPage() {
            SearchPage searchPage = mPageFactory.createSearchPage();
            addPage(searchPage);
            showPage(searchPage);
        }

        public void enterWebPage(String url) {
            List<Fragment> allPages = getAllPages();
            for (Fragment page : allPages) {
                if (page instanceof SearchPage) {
                    removePage(page);
                }
            }
            WebPage webPage = mPageFactory.createWebPage();
            addPage(webPage);
            showPage(webPage);
        }

        public void goBack() {
            List<Fragment> allPages = getAllPages();
            Fragment curPage = allPages.get(allPages.size() - 1);
            Fragment prePage = allPages.get(allPages.size() - 2);

            if (curPage instanceof WebPage) {
                WebPage webPage = (WebPage) curPage;
                if (webPage.canGoBack()) {
                    webPage.goBack();
                    return;
                }
            }

            removePage(curPage);
            showPage(prePage);
        }

        private void showPage(Fragment page) {
            List<Fragment> allPages = getAllPages();
            for (Fragment fragment : allPages) {
                if (fragment != page)
                    hidePage(fragment);
            }
            Window.this.showPage(page);
        }
    }

    /**
     * 当前窗口的Page生成工厂
     */
    public interface IPageFactory {
        HomePage createHomePage();

        WebPage createWebPage();

        SearchPage createSearchPage();
    }

    public final class PageFactory implements IPageFactory {
        @Override
        public HomePage createHomePage() {
            return HomePage.newInstance(Window.this);
        }

        @Override
        public WebPage createWebPage() {
            return WebPage.newInstance(Window.this);
        }

        @Override
        public SearchPage createSearchPage() {
            return SearchPage.newInstance(Window.this);
        }
    }

}