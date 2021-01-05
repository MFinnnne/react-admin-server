package com.df.pojo;

import com.df.config.StatusCode;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2020/10/7 17:58
 */
public class RestResult<T> {

    private boolean flag;
    private Integer status;
    private String message;
    private T data;

    public RestResult(boolean flag, StatusCode status, String message, T data) {
        this.flag = flag;
        this.setStatusValue(status);
        this.message = message;
        this.data = data;
    }

    public RestResult() {

    }

    private void setStatusValue(StatusCode status) {
        switch (status) {
            case FAILED:
                this.status = -1;
                break;
            case SUCCESS:
                this.status = 0;
                break;
            default:
                break;
        }
    }

    public RestResult(boolean flag, StatusCode status) {
        this.flag = flag;
        this.setStatusValue(status);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setStatusValue(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}