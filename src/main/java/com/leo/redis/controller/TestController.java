package com.leo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo Liu
 * @create 19/09/2019
 */
@RestController
@RequestMapping("api")
public class TestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("item")
    public void testOneSecondCanSetHowManyRecordInRedis(@RequestParam(required = false, defaultValue = "0") Integer count){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        long l = System.currentTimeMillis();
        while(System.currentTimeMillis() - l <= 1000){
            String a = String.valueOf(count++);
            operations.set(a, a);
        }
    }

    @GetMapping("item1")
    public int testOneSecondCanGetHowManyRecordInRedis(@RequestParam(required = false, defaultValue = "0") Integer count){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        long l = System.currentTimeMillis();
        int record = 0;
        while(System.currentTimeMillis() - l <= 1000){
            operations.get(String.valueOf(count++));
            record++;
        }
        return record;
    }
}