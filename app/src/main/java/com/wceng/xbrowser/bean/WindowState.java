package com.wceng.xbrowser.bean;

import androidx.fragment.app.Fragment;

public class WindowState {

    private Fragment page;
    private String url;
    private String title;

    public WindowState(Fragment page, String url, String title) {
        this.page = page;
        this.url = url;
        this.title = title;
    }

    public Fragment getPage() {
        return page;
    }

    public void setPage(Fragment page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "WindowState{" +
                "page=" + page +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
