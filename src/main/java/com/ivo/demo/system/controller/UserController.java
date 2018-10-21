package com.ivo.demo.system.controller;

import com.ivo.demo.common.BaseController;
import com.ivo.demo.common.JsonResult;
import com.ivo.demo.common.PageResult;
import com.ivo.demo.common.exception.ParameterException;
import com.ivo.demo.common.shiro.EndecryptUtil;
import com.ivo.demo.common.util.StringUtil;
import com.ivo.demo.system.entity.Role;
import com.ivo.demo.system.entity.User;
import com.ivo.demo.system.service.RoleService;
import com.ivo.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 * @author wangjian
 * @date 2018/9/1
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String user() {
        return "system/user.html";
    }

    @GetMapping("/editForm")
    public String addUser(Model model) {
        List<Role> roles;
        roles = roleService.list(false);
        model.addAttribute("roles", roles);
        return "system/user_form.html";
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public PageResult<User> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
            limit = 0;
        }
        if (StringUtil.isBlank(searchValue)) {
            searchKey = null;
        }
        return userService.list(page, limit, true, searchKey, searchValue);
    }

    /**
     * 添加用户
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(User user, String roleId) {
        user.setRoles(getRoles(roleId));
        user.setPassword("123456");
        if (userService.add(user)) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    /**
     * 修改用户
     **/
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(User user, String roleId) {
        user.setRoles(getRoles(roleId));
        if (userService.update(user)) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }

    private List<Role> getRoles(String roleStr) {
        List<Role> roles = new ArrayList<>();
        String[] split = roleStr.split(",");
        for (String t : split) {
            if (t.equals("1")) {
                throw new ParameterException("不能添加超级管理员");
            }
            roles.add(new Role(Integer.parseInt(t)));
        }
        return roles;
    }

    /**
     * 修改用户状态
     */
    @PostMapping("/updateState")
    @ResponseBody
    public JsonResult updateState(Integer userId, Integer state) {
        if (userService.updateState(userId, state)) {
            return JsonResult.ok();
        } else {
            return JsonResult.error();
        }
    }

    /**
     * 修改自己密码
     */
    @ResponseBody
    @PostMapping("/updatePsw")
    public JsonResult updatePsw(String oldPsw, String newPsw) {
        if ("admin".equals(getLoginUser().getUsername())) {
            return JsonResult.error("演示账号admin关闭该功能");
        }
        String finalSecret = EndecryptUtil.encrytMd5(oldPsw, getLoginUserName(), 3);
        if (!finalSecret.equals(getLoginUser().getPassword())) {
            return JsonResult.error("原密码输入不正确");
        }
        if (userService.updatePsw(getLoginUserId(), getLoginUserName(), newPsw)) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @ResponseBody
    @PostMapping("/restPsw")
    public JsonResult resetPsw(Integer userId) {
        User byId = userService.getById(userId);
        if (userService.updatePsw(userId, byId.getUsername(), "123456")) {
            return JsonResult.ok("重置成功");
        } else {
            return JsonResult.error("重置失败");
        }
    }
}
