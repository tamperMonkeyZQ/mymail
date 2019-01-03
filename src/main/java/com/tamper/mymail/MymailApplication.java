package com.tamper.mymail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.tamper.mymail.dao")
public class MymailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymailApplication.class, args);
    }

}

