package com.team.house.utils;

public class PageUtil {
    private int page=1;//当前页码
    private int rows;//页大小

    public PageUtil() {
    }

    public PageUtil(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}
