package com.home.HomeEnvironment.config.filter;

import com.alibaba.fastjson.JSON;
import com.home.HomeEnvironment.util.JwtTokenUtils;
import com.home.HomeEnvironment.util.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 鉴权过滤器
 * 查询当前用户具有什么角色和什么权限
 * 注意：确保过滤器的顺序
 * JWTAuthorizationFilter在JWTAuthenticationFilter后面就没问题
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在拦截器中获取token并解析，拿到用户信息，放置到SecurityContextHolder，这样便完成了springsecurity和jwt的整合。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain chain) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        String tokenHeader = httpServletRequest.getHeader(JwtTokenUtils.TOKEN_HEADER);
        //不对GET请求进行处理
//        if (httpServletRequest.getMethod().equals(String.valueOf(RequestMethod.GET))) {
//            chain.doFilter(httpServletRequest, httpServletResponse);
//            return;
//        }
        //如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null) {
//            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(403).setMessage("未检测到token").build()));
//            return;

            chain.doFilter(httpServletRequest, httpServletResponse);
            return;



//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            String str = "{\"token\":\"无Token\"}";
//            PrintWriter out;
//            try {
//                out = response.getWriter();
//                out.print(str);
//                out.flush();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        // 没有检测到Token
        if (StringUtils.isEmpty(tokenHeader)) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(403).setMessage("未检测到token").build()));
            return;
        }
        // Token格式错误
        if (!tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(403).setMessage("token格式错误").build()));
            return;
        }
        // Token不可解码
        tokenHeader = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        Claims claims = JwtTokenUtils.getTokenBody(tokenHeader);
        if (null == claims) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(403).setMessage("token验证失败").build()));
            return;
        }
        System.err.println(tokenHeader);
        //Token超时
        if (JwtTokenUtils.isExpiration(tokenHeader)) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(403).setMessage("token已经过期，请重新申请").build()));
            return;
        }
        //再进行一些必要的验证
        if (StringUtils.isEmpty(claims.getSubject())) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Response.Builder().setStatus(403).setMessage("token无效").build()));
            return;
        }
//        如果请求头中有token，则进行解析，并且设置认证信息
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(httpServletRequest, httpServletResponse, chain);
//        chain.doFilter(request, response);


    }

    /**
     * 这里从token中获取用户信息并新建一个token
     * @param tokenHeader
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        System.err.println(token);
        JwtTokenUtils.isExpiration(token);
        String role = JwtTokenUtils.getUserRole(token);
        if (null != username) {
            return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
//            Claims claims = Jwts.parser().setSigningKey("MyJwtSecret11").parseClaimsJws(token.replace("Bearer ", ""))
//                    .getBody();
//            String user  = claims.getSubject();
//            @SuppressWarnings("unchecked")
//            List<String> roles = claims.get("role", List.class);
//            List<SimpleGrantedAuthority> auth = roles.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
//
//            if (user != null) {
//                return new UsernamePasswordAuthenticationToken(user, null, auth);
//            }
//            return null;
        }
        return null;
    }
}
