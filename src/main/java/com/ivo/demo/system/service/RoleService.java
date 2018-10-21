package com.ivo.demo.system.service;

import com.ivo.demo.system.entity.Role;

import java.util.List;


/**
 * @author wangjian
 * @date 2018/9/1
 */
public interface RoleService {

    List<Role> getByUserId(Integer userId);

    List<Role> list(boolean showDelete);

    Role getById(Integer roleId);

    boolean add(Role role);

    boolean update(Role role);

    boolean updateState(Integer roleId, int isDelete);  // 逻辑删除

    boolean delete(Integer roleId);  // 物理删除
}
