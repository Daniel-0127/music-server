package com.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.music.dao"})
@ServletComponentScan
public class YzMusicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzMusicServerApplication.class, args);
    }

}
