package com.df;

import com.df.config.StorageProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan(basePackages={"com.df.mapper"})
@EnableConfigurationProperties(StorageProperties.class)
public class ReactAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactAdminServerApplication.class, args);
    }

}
