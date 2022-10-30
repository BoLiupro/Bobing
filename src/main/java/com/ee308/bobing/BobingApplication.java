package com.ee308.bobing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.ee308.bobing.mapper")
public class BobingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BobingApplication.class, args);
    }

}
