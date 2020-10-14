package com.df;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan(basePackages={"com.df.mapper"})
public class ReactAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactAdminServerApplication.class, args);
    }

}
