package com.ivo.demo.common.shiro;

import com.ivo.demo.common.util.StringUtil;
import com.ivo.demo.system.entity.Authorities;
import com.ivo.demo.system.entity.Role;
import com.ivo.demo.system.entity.User;
import com.ivo.demo.system.service.AuthoritiesService;
import com.ivo.demo.system.service.RoleService;
import com.ivo.demo.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro认证和授权
 * @author wangjian
 * @date 2018/9/2
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    @Lazy
    private AuthoritiesService authoritiesService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色
        List<Role> userRoles = roleService.getByUserId(user.getUserId());
        Set<String> roles = new HashSet<>();
        for (int i=0; i<userRoles.size(); i++) {
            roles.add(String.valueOf(userRoles.get(i).getRoleId()));
        }
        authorizationInfo.setRoles(roles);
        //权限
        List<Authorities> authorities = authoritiesService.listByUserId(user.getUserId());
        Set<String> permissions = new HashSet<>();
        for (int i=0; i<permissions.size(); i++) {
            String authority = authorities.get(i).getAuthority();
            if (StringUtil.isBlank(authority)) {
                permissions.add(authority);
            }
        }
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getByUsername(username);
        if(user == null) {
            //账号不存在
            throw new UnknownAccountException();
        }
        if(user.getState() != 0) {
            //账号被锁定
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()), getName());
        return authenticationInfo;
    }
}
