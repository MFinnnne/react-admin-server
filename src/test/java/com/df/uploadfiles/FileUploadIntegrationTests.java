package com.df.uploadfiles;

import com.df.pojo.RestResultTest;
import com.df.uploadfiles.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/5 23:26
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StorageService storageService;

    @LocalServerPort
    private int port;

    @Test
    public void shouldUploadFile() throws Exception {

        ClassPathResource resource = new ClassPathResource("testupload.txt", getClass());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("image", resource);
        ResponseEntity<String> response = this.restTemplate.postForEntity("/uploadFile", map,
                String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        then(storageService).should().store(any(MultipartFile.class));
    }

    @Test
    public void shouldDownloadFile() throws Exception {
        ClassPathResource resource = new ClassPathResource("testupload.txt", getClass());
        given(this.storageService.loadAsResource("testupload.txt")).willReturn(resource);
        ResponseEntity<String> response = this.restTemplate.getForEntity("/files/{filename}", String.class
                , "testupload.txt");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION))
                .isEqualTo("attachment; filename=\"testupload.txt\"");
        assertThat(response.getBody()).isEqualTo("Spring Framework");
    }

    @Test
    public void shouldDeleteFile() {
        given(this.storageService.delete("test.txt")).willReturn(1);
        ResponseEntity<RestResultTest> entity = this.restTemplate.exchange("/deleteFile/test.txt", HttpMethod.DELETE, null, RestResultTest.class);
        assertThat(Objects.requireNonNull(entity.getBody()).getData()).isEqualTo(1);
    }

}
