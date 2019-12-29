package com.ylf.manage.entity.BasePage;

import lombok.Data;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2019-12-27
 * @desc:
 */
public class ResPage<T> {
    private Integer total;
    private Integer pageSize = 10;
    private Integer pageNo;
    private List<T> result;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
