package com.home.HomeEnvironment.config.abnormal;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.HomeEnvironment.util.Response;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一处理被403响应的事件
 * 默认情况下登陆失败会跳转页面，这里自定义，同时判断是否ajax请求，是ajax请求则返回json，否则跳转失败页面
 */
@Configuration
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        /**
         * 判断是ajax请求还是普通请求
         * 如果是ajax请求则返回json数据
         * 如果是普通请求则跳转到login登录页
         */
        if (isAjaxRequest(httpServletRequest)) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(httpServletResponse.SC_FORBIDDEN).setMessage("请登录").build()));
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }

    /**
     * 判断是不是Ajax请求工具
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
