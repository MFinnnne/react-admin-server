package com.df;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/12 21:16
 **/
@SpringBootTest
@ActiveProfiles("test")
@EnableOpenApi
class ReactAdminServerApplicationTest {
    @Test
    public void smokeTest(){

    }
}