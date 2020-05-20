package com.jiudao.experiment.service;

import com.jiudao.experiment.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity findByUsername(String username);
}
