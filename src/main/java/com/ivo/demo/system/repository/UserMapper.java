package com.ivo.demo.system.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ivo.demo.system.entity.User;

/**
 * @author wangjian
 * @date 2018/9/1
 */
public interface UserMapper extends BaseMapper<User> {

    User getByUsername(String username);
}
