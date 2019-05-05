package com.home.HomeEnvironment.web;

import com.alibaba.fastjson.JSON;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.service.SysUserService;
import com.home.HomeEnvironment.util.JsonResult;
import com.home.HomeEnvironment.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @PostMapping(value = "/register")
    public JsonResult regin(SysUser sysUser) {
        sysUserService.register(sysUser);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMsg("注册成功");
        return jsonResult;
    }

    @GetMapping(value = "getUserAll")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public List<SysUser> UserAll(){
        return sysUserService.findAll();
    }

    @GetMapping(value = "getUserAllpub")
    public List<SysUser> UserAllpub(){
        return sysUserService.findAll();
    }

    @PostMapping(value = "setSession")
    public String insertSession(HttpServletRequest request) {
        request.getSession().setAttribute("userSession",1);
        return "添加Session成功！";
    }

    @GetMapping(value = "getSession")
    public String selectSession(HttpServletRequest request) {
        int session = (int)request.getSession().getAttribute("userSession");
        return "获取Session成功！"+ session;
    }

}
