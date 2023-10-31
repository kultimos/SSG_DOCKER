package com.kul.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kul.pojo.User;
import org.springframework.stereotype.Service;


public interface IUserService extends IService<User> {

    User selectOne(Long id);
    void insert(User user);

}
