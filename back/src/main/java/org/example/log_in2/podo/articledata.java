package org.example.log_in2.podo;

import java.util.List;

public class articledata {
    private List<article> articles;
    private boolean success;

    public articledata() {
    }

    public articledata(List<article> articles, boolean success) {
        this.articles = articles;
        this.success = success;
    }

    /**
     * 获取
     * @return articles
     */
    public List<article> getArticles() {
        return articles;
    }

    /**
     * 设置
     * @param articles
     */
    public void setArticles(List<article> articles) {
        this.articles = articles;
    }

    /**
     * 获取
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return "articledata{articles = " + articles + ", success = " + success + "}";
    }
}
