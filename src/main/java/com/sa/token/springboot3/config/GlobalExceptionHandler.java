package com.sa.token.springboot3.config;

import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2024/7/23 下午4:13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        log.error("全局异常拦截=> {}", e.getMessage());
        return SaResult.error(e.getMessage());
    }
}
