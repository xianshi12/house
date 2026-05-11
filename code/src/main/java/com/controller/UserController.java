package com.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.UserEntity;
import com.service.TokenService;
import com.service.UserService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 用户
 */
@RequestMapping({"user", "yonghu"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, HttpServletRequest request) {
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("account", username));
        if (user == null || user.getPassword() == null || !user.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        Map<String, String> tokens = tokenService.generateToken(user.getId(), user.getAccount(), "user", "用户");
        return R.ok().put("token", tokens.get("token")).put("refreshToken", tokens.get("refreshToken"));
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody UserEntity user) {
        if (user.getAccount() == null || user.getAccount().trim().length() == 0) {
            return R.error("账号不能为空");
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return R.error("密码不能为空");
        }
        if (userService.selectOne(new EntityWrapper<UserEntity>().eq("account", user.getAccount())) != null) {
            return R.error("用户已存在");
        }
        userService.insert(user);
        return R.ok();
    }

    /**
     * 刷新token
     */
    @IgnoreAuth
    @RequestMapping(value = "/refresh")
    public R refresh(String refreshToken) {
        Map<String, String> tokens = tokenService.refreshToken(refreshToken);
        if (tokens == null) {
            return R.error("刷新令牌无效或已过期");
        }
        return R.ok().put("token", tokens.get("token")).put("refreshToken", tokens.get("refreshToken"));
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username) {
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("account", username));
        if (user == null) {
            return R.error("账号不存在");
        }
        user.setPassword("123456");
        userService.updateById(user);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, UserEntity user) {
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
        PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(UserEntity user) {
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
        ew.allEq(MPUtil.allEQMapPre(user, "user"));
        return R.ok().put("data", userService.selectListView(ew));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserEntity user) {
        if (userService.selectOne(new EntityWrapper<UserEntity>().eq("account", user.getAccount())) != null) {
            return R.error("用户已存在");
        }
        userService.insert(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user) {
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("account", user.getAccount()));
        if (u != null && !u.getId().equals(user.getId())) {
            return R.error("用户名已存在。");
        }
        userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        userService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
