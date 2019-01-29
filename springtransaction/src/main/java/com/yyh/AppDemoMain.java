package com.yyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.yyh.mapper")
@EnableTransactionManagement
public class AppDemoMain {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(AppDemoMain.class, args);
    }

}
