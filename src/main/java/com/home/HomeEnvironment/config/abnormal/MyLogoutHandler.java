package com.home.HomeEnvironment.config.abnormal;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义登出（注销token）用户时重定向到指定的url上
 */
@Component
public class MyLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       Authentication authentication) {
        try {
            String uri = httpServletRequest.getParameter("uri");
            httpServletResponse.sendRedirect(uri);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
