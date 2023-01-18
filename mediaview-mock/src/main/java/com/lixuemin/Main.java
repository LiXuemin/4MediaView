package com.lixuemin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @description ${description}
 * @author: lixuemin
 * @date: ${YEAR}-${MONTH}-${DAY}
 **/
@SpringBootApplication
@EnableWebMvc
@RestController
public class Main {
    @RequestMapping("/hello")
    public String sayHello() {
        return "hello,4meidaview";
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
}