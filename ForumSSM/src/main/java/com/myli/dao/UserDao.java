package com.myli.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myli.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
