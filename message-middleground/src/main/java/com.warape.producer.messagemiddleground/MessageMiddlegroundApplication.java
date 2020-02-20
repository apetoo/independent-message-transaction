package com.warape.producer.messagemiddleground;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanmingyu
 */
@SpringBootApplication
@MapperScan(value = "com.warape.producer.messagemiddleground.mapper")
public class MessageMiddlegroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageMiddlegroundApplication.class, args);
    }

}
