package com.sa.token.springboot3.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二级认证
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2024/7/24 上午9:37
 */
@RestController
@RequestMapping("/level2")
public class Level2CertificationController {
    // 删除仓库
    @RequestMapping("/deleteProject")
    public SaResult deleteProject(String projectId) {
        // 第1步，先检查当前会话是否已完成二级认证
        if(!StpUtil.isSafe()) {
            return SaResult.error("仓库删除失败，请完成二级认证后再次访问接口");
        }

        // 第2步，如果已完成二级认证，则开始执行业务逻辑
        // ...

        // 第3步，返回结果
        return SaResult.ok("仓库删除成功");
    }

    // 提供密码进行二级认证
    @RequestMapping("/openSafe")
    public SaResult openSafe(String password) {
        // 比对密码（此处只是举例，真实项目时可拿其它参数进行校验）
        if("123456".equals(password)) {
            // 比对成功，为当前会话打开二级认证，有效期为120秒
            StpUtil.openSafe(120);
            return SaResult.ok("二级认证成功");
        }

        // 如果密码校验失败，则二级认证也会失败
        return SaResult.error("二级认证失败");
    }

}
