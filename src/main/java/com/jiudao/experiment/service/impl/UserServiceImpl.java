package com.jiudao.experiment.service.impl;

import com.jiudao.experiment.entity.UserEntity;
import com.jiudao.experiment.mapper.UserMapper;
import com.jiudao.experiment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity findByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
