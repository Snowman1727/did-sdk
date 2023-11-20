package com.teleinfo.didsdk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableScheduling
@MapperScan("com.teleinfo.didsdk.mapper")
@ServletComponentScan
public class DidSdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DidSdkApplication.class, args);
    }

}
