package com.df.uploadfiles.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/4 20:41
 **/
@Service
public interface StorageService {

    /**
     * Init.
     */
    void init();

    /**
     * Store.
     *
     * @param file the file
     * @return the String
     */
    FileUploadResponse store(MultipartFile file);

    /**
     * Load all stream.
     *
     * @return the stream
     */
    Stream<Path> loadAll();

    /**
     * Load path.
     *
     * @param filename the filename
     * @return the path
     */
    Path load(String filename);

    /**
     * 读取资源
     * @param filename 文件名
     * @return 资源
     */
    Resource loadAsResource(String filename);

    /**
     * Delete all.
     */
    void deleteAll();


    /**
     * 删除文件
     * @param filename 文件名
     * @return 0 成功 1 失败
     * @throws FileNotFoundException  文件未找到异常
     */
    int delete(String filename) ;
}
