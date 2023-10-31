package com.kul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tengyer 2022/05/06 16:34
 */
@SpringBootApplication
@MapperScan("com.kul.mapper") // 扫描我们的mapper文件夹
public class DockerBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerBootApplication.class, args);
    }
}