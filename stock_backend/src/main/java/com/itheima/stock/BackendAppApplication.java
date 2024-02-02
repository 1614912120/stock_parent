package com.itheima.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itheima.stock.mapper")  //扫描持久层mapper 生成代理对象 维护到ioc中
public class BackendAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendAppApplication.class, args);
    }
}
