package com.jztey.framework.mvc;

import java.util.List;

/**
 * Created by Charles on 2015/10/15.
 *
 * @param <T>
 */
public class RestfulPagingResult<T> extends RestfulResult<T> {
    private int total = 0;

    public RestfulPagingResult(List<T> data, int total) {
        super(data);
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
