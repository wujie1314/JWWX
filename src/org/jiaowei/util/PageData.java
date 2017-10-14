package org.jiaowei.util;

import java.util.List;

/**
 * Created by alex on 15-5-5.
 */
public class PageData {
    private Long total;
    private List rows;
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
