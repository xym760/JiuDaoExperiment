package com.jiudao.experiment.mapper;

import com.jiudao.experiment.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper {

    UserEntity getUserByUsername(@Param("username") String username);
}
