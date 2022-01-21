package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 16019
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-01-19 17:48:34
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




