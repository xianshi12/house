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
import com.entity.DeveloperEntity;
import com.service.DeveloperService;
import com.service.TokenService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 开发商
 */
@RequestMapping({"developer", "kaifashang"})
@RestController
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, HttpServletRequest request) {
        DeveloperEntity developer = developerService.selectOne(new EntityWrapper<DeveloperEntity>().eq("developer_code", username));
        if (developer == null || developer.getPassword() == null || !developer.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        Map<String, String> tokens = tokenService.generateToken(developer.getId(), developer.getDeveloperCode(), "developer", "开发商");
        return R.ok().put("token", tokens.get("token")).put("refreshToken", tokens.get("refreshToken"));
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody DeveloperEntity developer) {
        if (developer.getDeveloperCode() == null || developer.getDeveloperCode().trim().length() == 0) {
            return R.error("开发商号不能为空");
        }
        if (developer.getPassword() == null || developer.getPassword().trim().length() == 0) {
            return R.error("密码不能为空");
        }
        if (developerService.selectOne(new EntityWrapper<DeveloperEntity>().eq("developer_code", developer.getDeveloperCode())) != null) {
            return R.error("开发商已存在");
        }
        developerService.insert(developer);
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
        return R.ok("退出成功");
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username) {
        DeveloperEntity developer = developerService.selectOne(new EntityWrapper<DeveloperEntity>().eq("developer_code", username));
        if (developer == null) {
            return R.error("账号不存在");
        }
        developer.setPassword("123456");
        developerService.updateById(developer);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, DeveloperEntity developer) {
        EntityWrapper<DeveloperEntity> ew = new EntityWrapper<DeveloperEntity>();
        PageUtils page = developerService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, developer), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(DeveloperEntity developer) {
        EntityWrapper<DeveloperEntity> ew = new EntityWrapper<DeveloperEntity>();
        ew.allEq(MPUtil.allEQMapPre(developer, "developer"));
        return R.ok().put("data", developerService.selectListView(ew));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        DeveloperEntity developer = developerService.selectById(id);
        return R.ok().put("data", developer);
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request) {
        Long id = (Long) request.getAttribute("userId");
        DeveloperEntity developer = developerService.selectById(id);
        return R.ok().put("data", developer);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody DeveloperEntity developer) {
        if (developerService.selectOne(new EntityWrapper<DeveloperEntity>().eq("developer_code", developer.getDeveloperCode())) != null) {
            return R.error("开发商已存在");
        }
        developerService.insert(developer);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DeveloperEntity developer) {
        DeveloperEntity d = developerService.selectOne(new EntityWrapper<DeveloperEntity>().eq("developer_code", developer.getDeveloperCode()));
        if (d != null && !d.getId().equals(developer.getId())) {
            return R.error("开发商号已存在。");
        }
        developerService.updateById(developer);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        developerService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
