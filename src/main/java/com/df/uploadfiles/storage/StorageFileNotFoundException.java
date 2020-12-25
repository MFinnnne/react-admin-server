package com.df.uploadfiles.storage;


/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/4 23:18
 **/
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }

}
