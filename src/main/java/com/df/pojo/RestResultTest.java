package com.df.pojo;

import com.df.config.StatusCode;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/12/22 22:09
 **/
public class RestResultTest extends RestResult<Integer> {

    public RestResultTest() {
        super();
    }

    public RestResultTest(boolean flag, StatusCode status, String message, Integer data) {
        super(flag, status, message, data);
    }

    public RestResultTest(boolean flag, StatusCode status) {
        super(flag, status);
    }

    @Override
    public boolean isFlag() {
        return super.isFlag();
    }

    @Override
    public void setFlag(boolean flag) {
        super.setFlag(flag);
    }

    @Override
    public void setStatusValue(Integer status) {
        super.setStatusValue(status);
    }

    @Override
    public Integer getData() {
        return super.getData();
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
    }

    @Override
    public Integer getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(Integer status) {
        super.setStatus(status);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }
}
