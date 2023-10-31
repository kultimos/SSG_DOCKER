package com.kul.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import com.kul.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    public User selectOne(Long id) {
        User user = null;
        String key = "user:" + id;
        user = (User) redisTemplate.opsForValue().get(key);
        if(user == null) {
            user = userMapper.selectById(id);
            if(user == null) {
                return null;
            } else {
                user.setUsername(UUID.randomUUID().toString());
                redisTemplate.opsForValue().set(key, user);
            }
        }
        return userMapper.selectById(id);
    }

    public void insert(User user) {
        int insert = userMapper.insert(user);
        if(insert > 0) {
            user = userMapper.selectById(user.getId());
            String key = "user:" + user.getId();
            redisTemplate.opsForValue().set(key, user);
        }
    }
}
