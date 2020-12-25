package com.df.uploadfiles.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/4 23:35
 **/

@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
