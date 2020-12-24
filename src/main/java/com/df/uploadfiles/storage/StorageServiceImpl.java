package com.df.uploadfiles.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/4 20:47
 **/
@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Value("${server.port}")
    private int portNumber;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage");
        }
    }

    @Override
    public FileUploadResponse store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file" + file.getOriginalFilename());
            }
            String fileName = file.getOriginalFilename();
            if (!Objects.equals(file.getContentType(), "text/plain")) {
                String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
                String suffix = split[split.length - 1];
                fileName = "image-" + UUID.randomUUID() + "." + suffix;
            }
            assert fileName != null;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
            return new FileUploadResponse("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + portNumber, fileName);
        } catch (Exception e) {
            throw new StorageException("Failed to store file" + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public int delete(String filename) throws FileNotFoundException {
        try {
            Files.deleteIfExists(this.rootLocation.resolve(filename));
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException();
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
