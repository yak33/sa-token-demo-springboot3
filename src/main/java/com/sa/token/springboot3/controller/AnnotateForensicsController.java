package com.sa.token.springboot3.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解鉴权
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2024/7/23 下午4:07
 */
@RestController
@RequestMapping("/anfo")
public class AnnotateForensicsController {
    // 登录校验：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        System.out.println("------- [身份临时切换]调用开始...");
        StpUtil.switchTo(10044, () -> {
            System.out.println("是否正在身份临时切换中: " + StpUtil.isSwitch());  // 输出 true
            System.out.println("获取当前登录账号id: " + StpUtil.getLoginId());   // 输出 10044
        });
        System.out.println("------- [身份临时切换]调用结束...");
        return "查询用户信息";
    }

    // 角色校验：必须具有指定角色才能进入该方法
    @SaCheckRole("super-admin")
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }

    // 注解式鉴权：只要具有其中一个权限即可通过校验
    @RequestMapping("/atJurOr")
    @SaCheckPermission(value = {"user.add", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }

    // 角色权限双重 “or校验”：具备指定权限或者指定角色即可通过校验
    @RequestMapping("/userAdd")
    @SaCheckPermission(value = "user-add", orRole = "admin")
    public SaResult userAdd() {
        return SaResult.data("用户信息");
    }

    // 权限校验：必须具有指定权限才能进入该方法
//    @SaCheckPermission("user-add")
//    @RequestMapping("add")
//    public String add() {
//        return "用户增加";
//    }
//
//    // 二级认证校验：必须二级认证之后才能进入该方法
//    @SaCheckSafe()
//    @RequestMapping("add")
//    public String add() {
//        return "用户增加";
//    }
//
//    // Http Basic 校验：只有通过 Http Basic 认证后才能进入该方法
//    @SaCheckHttpBasic(account = "sa:123456")
//    @RequestMapping("add")
//    public String add() {
//        return "用户增加";
//    }
//
//    // Http Digest 校验：只有通过 Http Digest 认证后才能进入该方法
//    @SaCheckHttpDigest(value = "sa:123456")
//    @RequestMapping("add")
//    public String add() {
//        return "用户增加";
//    }

    // 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @SaCheckDisable("comment")
    @RequestMapping("send")
    public String send() {
        return "查询用户信息";
    }

}
