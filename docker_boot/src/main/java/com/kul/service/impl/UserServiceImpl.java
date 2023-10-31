package com.kul.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import com.kul.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    public User selectOne(Long id) {
        User user = null;
        String key = "user:" + id;
        user = (User) redisGet(key);
        if(user == null) {
            user = userMapper.selectById(id);
            if(user == null) {
                return null;
            } else {
                user.setUsername(UUID.randomUUID().toString());
                redisInt(key, user);
            }
        }
        return userMapper.selectById(id);
    }

    public void insert(User user) {
        int insert = userMapper.insert(user);
        if(insert > 0) {
            user = userMapper.selectById(user.getId());
            String key = "user:" + user.getId();
            redisInt(key,user);
        }
    }

    private void redisInt(String key,Object value) {
        Jedis jedis = new Jedis("192.168.10.131");
        Gson gson = new Gson();
        String json = gson.toJson(value);
        // 存储数据
        jedis.set(key, json);
        // 关闭连接
        jedis.close();
    }

    private Object redisGet(String key) {
        Jedis jedis = new Jedis("192.168.10.131");
        String s = jedis.get(key);
        Gson gson = new Gson();
        User user = gson.fromJson(s, User.class);
        // 存储数据

        // 关闭连接
        jedis.close();
        return user;
    }

}
