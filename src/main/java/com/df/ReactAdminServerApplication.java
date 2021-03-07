package com.df;

import com.df.uploadfiles.storage.StorageProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan(basePackages={"com.df.mapper"})
@EnableConfigurationProperties(StorageProperties.class)
@Configuration
@EnableAdminServer
public class ReactAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactAdminServerApplication.class, args);
    }


}
