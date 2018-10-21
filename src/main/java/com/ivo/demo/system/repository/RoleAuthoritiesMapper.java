package com.ivo.demo.system.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ivo.demo.system.entity.RoleAuthorities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/9/1
 */
public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    int insertRoleAuths(@Param("roleId") Integer roleId, @Param("authIds") List<Integer> authIds);

}
