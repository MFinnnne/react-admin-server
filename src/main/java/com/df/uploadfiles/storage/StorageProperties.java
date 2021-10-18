package com.df.uploadfiles.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/4 23:35
 **/

@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "";

    public String getLocation() {
        File file = new File("");
        location  = file.getAbsolutePath()+"/upload-dir/";
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
