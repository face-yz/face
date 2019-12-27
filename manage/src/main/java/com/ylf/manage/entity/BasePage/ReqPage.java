package com.ylf.manage.entity.BasePage;


/**
 * @author: leifeng.ye
 * @date: 2019-12-27
 * @desc:
 */
public class ReqPage {
    private Integer pageSize=10;
    private Integer pageNo;

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
}
