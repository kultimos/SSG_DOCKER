package com.kul.controller;


import com.kul.pojo.User;
import com.kul.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author tengyer 2022/05/06 16:35
 */
@RestController
public class OrderController {
    @Value("${server.port}")
    private String port;

    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/order/docker")
    public String helloDocker() {
        redisTemplate.opsForValue().set("nihao","wushuai");
        return "hello world \t" + port + "\t" + UUID.randomUUID().toString();
    }

    @RequestMapping(value = "/order/index", method = RequestMethod.GET)
    public String index() {
        return "服务端口号：" + "\t" + port + "\t" + UUID.randomUUID().toString();
    }
}