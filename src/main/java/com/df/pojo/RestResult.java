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
    private Object data;

    public RestResult(boolean flag, StatusCode statusCode, T data) {
        this.flag = flag;
        setStatus(statusCode);
        this.data = data;
    }

    public RestResult(boolean flag, Integer status, Object data) {
        this.flag = flag;
        this.status = status;
        this.data = data;
    }

    private void setStatus(StatusCode status) {
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
        this.setStatus(status);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}