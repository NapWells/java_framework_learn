package com.yyh;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author lhy
 * @Date 2018/10/19 17:18
 * @JDK 1.7
 * @Description TODO
 */
@SpringBootApplication
@MapperScan("com.yyh.mapper")
@Slf4j
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MainApp.class,args);
    }
}
