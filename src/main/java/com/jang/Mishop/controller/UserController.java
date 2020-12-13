package com.jang.Mishop.controller;


import com.jang.Mishop.entity.User;
import com.jang.Mishop.service.UserService;
import com.jang.Mishop.util.BeanUtil;
import com.jang.Mishop.util.CookieUtil;
import com.jang.Mishop.util.MD5Util;
import com.jang.Mishop.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@RestController
@RequestMapping("/Mishop/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public ResultMessage register(@RequestBody User user) {
        try {
            userService.register(user);
            resultMessage.success("200", "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMessage;
    }


    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public ResultMessage login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        userService.login(user);
        // 添加cookie，设置唯一认证
        String encode = MD5Util.MD5Encode(user.getUsername() + user.getPassword(), "UTF-8");
        // 进行加盐
        encode += "|" + user.getUserId() + "|" + user.getUsername() + "|";
        CookieUtil.setCookie(request, response, "XM_TOKEN", encode, 1800);
        // 将encode放入redis中，用于认证
        try {
            redisTemplate.opsForHash().putAll(encode, BeanUtil.bean2map(user));
            redisTemplate.expire(encode, 30 * 60, TimeUnit.SECONDS); // 设置过期时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将密码设为null,返回给前端
        user.setPassword(null);
        resultMessage.success("200", "登录成功", user);
        return resultMessage;

    }

}

