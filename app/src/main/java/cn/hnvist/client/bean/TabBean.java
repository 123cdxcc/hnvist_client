package cn.hnvist.client.bean;

import androidx.fragment.app.Fragment;

public class TabBean {
    private int id;
    private String title;
    private Fragment fragment;

    public TabBean(int id, String title, Fragment fragment) {
        this.id = id;
        this.title = title;
        this.fragment = fragment;
    }

    public TabBean(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
