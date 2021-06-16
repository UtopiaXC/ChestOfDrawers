package com.utopiaxc.chest.beans;

public class BeanWeibo {
    int order;
    String title;
    String url;

    public BeanWeibo(int order, String title, String url) {
        this.order = order;
        this.title = title;
        this.url = url;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
