package com.df.uploadfiles;

import com.df.uploadfiles.storage.DeleteFileNotFoundException;
import com.df.uploadfiles.storage.StorageFileNotFoundException;
import com.df.uploadfiles.storage.StorageService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storageService;

    @Test
    public void shouldListAllFiles() throws Exception {


        given(this.storageService.loadAll())
                .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));
        assertEquals("[\"http://localhost/files/first.txt\",\"http://localhost/files/second.txt\"]", this.mvc.perform(get("/getFiles").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(multipart("/uploadFile").file(multipartFile))
                .andExpect(status().isOk())
                .andExpect(content().string("store success"));

        then(this.storageService).should().store(multipartFile);
    }

    @Test
    public void shouldSaveDeleteFiles() throws Exception {
        given(storageService.delete("test-delete.txt")).willReturn(1);
        ResultActions actions = this.mvc.perform(delete("/deleteFile/test-delete.txt").accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.flag", Matchers.equalTo(true)));
        then(this.storageService).should().delete("test-delete.txt");


    }

    @Test
    public void should404WhenDeletedFileNotExist() throws Exception {
        given(storageService.delete("test-delete.txt")).willThrow(DeleteFileNotFoundException.class);
        this.mvc.perform(delete("/deleteFile/test-delete.txt")).andExpect(status().isNotFound());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should404WhenMissingFile() throws Exception {
        given(this.storageService.loadAsResource("test.txt"))
                .willThrow(StorageFileNotFoundException.class);
        this.mvc.perform(get("/files/test.txt")).andExpect(status().isNotFound());
    }

}
