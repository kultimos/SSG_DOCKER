package com.kul;

import com.kul.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MybatisTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.selectList(null).forEach((item) -> System.out.println(item));

    }
}
