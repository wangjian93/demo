package com.ivo.demo.system.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ivo.demo.system.entity.Role;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/9/1
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectByUserId(Integer userId);
}
