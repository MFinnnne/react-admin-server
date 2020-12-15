package com.df.uploadfiles.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
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
     * @param filename 文件名
     * @return 资源
     */
    Resource loadAsResource(String filename);

    /**
     * Delete all.
     */
    void deleteAll();
}
