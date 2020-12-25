package com.df.uploadfiles;

import com.df.uploadfiles.storage.StorageProperties;
import com.df.uploadfiles.storage.StorageException;
import com.df.uploadfiles.storage.StorageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/5 20:57
 **/

class StorageServiceImplTest {

    private final StorageProperties properties = new StorageProperties();
    private StorageServiceImpl service;

    @BeforeEach
    void init() {
        properties.setLocation("D:\\study road\\React\\file\\files" + Math.abs(new Random().nextLong()));
        service = new StorageServiceImpl(properties);
        service.init();
    }

    @Test
    public void loadNonExistent() {

        assertThat(service.load("foo.txt")).doesNotExist();
    }

    @Test
    public void saveAndLoad() {
        service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello, World".getBytes()));
        assertThat(service.load("foo.txt")).exists();
    }

    @Test
    public void saveRelativePathNotPermitted() {
        assertThrows(StorageException.class, () -> service.store(new MockMultipartFile("foo", "../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes())));
    }

    @Test
    public void saveAbsolutePathNotPermitted() {
        assertThrows(StorageException.class, () -> service.store(new MockMultipartFile("foo", "/etc/passwd",
                MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes())));
    }

    @Test
    @EnabledOnOs({OS.LINUX})
    public void saveAbsolutePathInFilenamePermitted() {
        //Unix file systems (e.g. ext4) allows backslash '\' in file names.
        String fileName = "\\etc\\passwd";
        service.store(new MockMultipartFile(fileName, fileName,
                MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
        assertTrue(Files.exists(
                Paths.get(properties.getLocation()).resolve(Paths.get(fileName))));
    }

    @Test
    public void savePermitted() {
        service.store(new MockMultipartFile("foo", "bar/../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
    }

    @Test
    public void delete() throws FileNotFoundException {
        service.store(new MockMultipartFile("foo", "bar/../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
        assertThat(service.load("foo.txt")).exists();
        service.delete("foo.txt");
        assertThat(service.load("foo.txt")).doesNotExist();
    }


}