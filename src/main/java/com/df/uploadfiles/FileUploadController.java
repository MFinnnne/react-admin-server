package com.df.uploadfiles;

import com.df.config.StatusCode;
import com.df.pojo.RestResult;
import com.df.uploadfiles.storage.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author MFine
 */
@RestController
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @ApiOperation(value = "获取所有图片")
    @GetMapping("/images")
    public ResponseEntity<List<String>> listUploadedFiles() {

        List<String> files = storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(files);

    }

    @ApiOperation(value = "根据图片名称获取图片")
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<RestResult<FileUploadResponse>> handleFileUpload(@RequestParam("image") MultipartFile file) {
        FileUploadResponse uploadResponse = storageService.store(file);
        return ResponseEntity.ok().body(new RestResult<>(true, StatusCode.SUCCESS, "success", uploadResponse));
    }


    @DeleteMapping("/deleteFile/{filename:.+}")
    public ResponseEntity<RestResult<Integer>> deleteFile(@PathVariable String filename) {
        int delete = storageService.delete(filename);
        return ResponseEntity.ok().body(new RestResult<>(true, StatusCode.SUCCESS, "", delete));
    }

    @ExceptionHandler({DeleteFileNotFoundException.class, StorageFileNotFoundException.class})
    public ResponseEntity<?> handleStorageFileNotFound(StorageException exc) {
        return ResponseEntity.notFound().build();
    }

}
