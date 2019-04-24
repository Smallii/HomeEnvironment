//package com.home.HomeEnvironment.config.abnormal;
//
//import com.alibaba.fastjson.JSON;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 登录失败处理类
// */
//@Component
//public class LoginFailureHandler implements AuthenticationFailureHandler {
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                                        HttpServletResponse httpServletResponse,
//                                        AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.setStatus(httpServletResponse.SC_FORBIDDEN);
//        String reason = "用户名或密码错误："+ e.getMessage();
//        httpServletResponse.getWriter().write(JSON.toJSONString(new ObjectMapper().writeValueAsString(reason)));
//    }
//}
