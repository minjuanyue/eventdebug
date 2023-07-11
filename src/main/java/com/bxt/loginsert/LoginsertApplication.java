package com.bxt.loginsert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bxt.loginsert.domain.repository")
@SpringBootApplication(scanBasePackages = {"com.bxt"})
public class LoginsertApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginsertApplication.class, args);
    }

}
