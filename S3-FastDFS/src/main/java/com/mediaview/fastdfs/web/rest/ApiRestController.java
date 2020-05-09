package com.mediaview.fastdfs.web.rest;

import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * @program: s-3-fast-dfs
 *
 * @description: s3 api
 *
 * @author: LiXuemin
 *
 * @create: 2020-04-29
 **/
public class ApiRestController {

    @PostConstruct
    public void initFastDFSConnectionPool() {

    }

    @GetMapping("/")
    public String getObject() {
        return null;
    }

    @PostMapping("/")
    public String postObject() {
        return null;
    }

    @PutMapping("/")
    public String putObject() {
        return null;
    }
    @DeleteMapping("/")
    public String deleteObject() {
        return null;
    }

}
