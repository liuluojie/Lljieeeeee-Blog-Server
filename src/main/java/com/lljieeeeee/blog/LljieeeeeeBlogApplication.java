package com.lljieeeeee.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lljieeeeee
 * @date 2021/11/22 22:30
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Slf4j
@SpringBootApplication
public class LljieeeeeeBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LljieeeeeeBlogApplication.class, args);
        log.info("项目启动成功");
    }
}
