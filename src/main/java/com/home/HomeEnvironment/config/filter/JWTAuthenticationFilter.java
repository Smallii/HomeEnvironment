package com.home.HomeEnvironment.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 认证过滤器
 * 用户账号的验证
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    /**
     * 使用Spring Security
     * 默认的登录地址是/login
     * 可以自定义登录地址
     * @param authenticationManager
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        //自定义登录连接
//        super.setFilterProcessesUrl("/auth/login");
    }

    /**
     * 获取到登录的信息
     * 包括用户名称
     * 密码
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
        );
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     * @param request
     * @param response
     * @param chain
     * @param auth
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) {
        //获取登录成功的用户信息
        SysUser sysUser = (SysUser) auth.getPrincipal();
        System.err.println("登录成功，登录用户的信息是：" + sysUser.toString());
        //返回创建成功的token，但是这里创建的token只是单纯的token，按照jwt的规定，最后请求的格式应该是 `Bearer token`
        String role = "";
        Collection<? extends GrantedAuthority> authorities = sysUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
//        Claims claims = Jwts.claims();
//        claims.put("role", auth.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
        String token = JwtTokenUtils.createToken(sysUser.getUsername(), role, false);
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
//        Claims claims = Jwts.claims();
//        claims.put("role", auth.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setSubject(auth.getName())
//                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
//                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret11").compact();
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        String str = "{\"token\":\"" + token + "\"}";
//        PrintWriter out;
//        try {
//            out = response.getWriter();
//            out.print(str);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 验证失败时候调用的方法
     * @param httpServletrequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest httpServletrequest,
                                              HttpServletResponse httpServletResponse,
                                              AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setStatus(httpServletResponse.SC_FORBIDDEN);
        String reason = "身份验证失败，用户名或密码错误："+ e.getMessage();
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(reason));
    }

}
