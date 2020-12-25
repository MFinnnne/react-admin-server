package com.df.uploadfiles.storage;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/4 23:26
 **/
public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
