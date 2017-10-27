package cn.free.domain;

import java.util.List;

/**
 *  分页类
 */
public class Page {
    private int totalItems;         //全部条目数
    private int page;               //分成了多少页
    private int limit;              //一页多少条数据
    private int totalPages;         //最大页面数
    private List<Ip> IpList;          //一页全部的登录信息集合

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Ip> getIpList() {
        return IpList;
    }

    public void setIpList(List<Ip> ipList) {
        IpList = ipList;
    }
}
