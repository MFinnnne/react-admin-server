package com.df.uploadfiles.storage;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/12/25 21:07
 **/
public class DeleteFileNotFoundException  extends StorageException{

    public DeleteFileNotFoundException(String message) {
        super(message);
    }

    public DeleteFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
