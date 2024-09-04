package org.earthdog.achilles.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ObjectName;

/**
 * @Date 2024/9/1 19:54
 * @Author DZN
 * @Desc TestController
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redis")
    public String redisTest() {
        redisTemplate.opsForValue().set("k", "v");
        return "";
    }

}
