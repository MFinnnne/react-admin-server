package com.df.pojo;

import com.df.config.StatusCode;

import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 22:27
 **/
public class RestResultList extends RestResult<List<Role>>{

    public RestResultList(boolean flag, StatusCode status, String message, List<Role> data) {
        super(flag, status, message, data);
    }

    public RestResultList() {
        super();
    }

    public RestResultList(boolean flag, StatusCode status) {
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
    public List<Role> getData() {
        return super.getData();
    }

    @Override
    public void setData(List<Role> data) {
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
