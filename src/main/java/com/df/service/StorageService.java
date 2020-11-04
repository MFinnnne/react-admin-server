package com.df.service;

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

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    /**
     * @param filename 文件名
     * @return 资源
     */
    Resource loadAsResource(String filename);

    void deleteAll();
}
