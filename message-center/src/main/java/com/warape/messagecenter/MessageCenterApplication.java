package com.warape.messagecenter;


import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanmingyu
 */
@SpringBootApplication
@MapperScan(value = "com.warape.messagecenter.mapper")
@DubboComponentScan(basePackages = "com.warape.messagecenter.services")
public class MessageCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageCenterApplication.class, args);
    }

}
