package com.df.uploadfiles.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/12/15 21:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String url;
    private String name;
}
