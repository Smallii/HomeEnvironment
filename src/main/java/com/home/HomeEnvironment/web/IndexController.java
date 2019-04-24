package com.home.HomeEnvironment.web;

import com.home.HomeEnvironment.entity.MsgEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 创建时间：2018年8月22日10点32分
 * 创建人：翟丰
 * 功能描述：验证用户登陆跳转页面
 */
@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index(Model model) {
        MsgEntity msg = new MsgEntity("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }

    @GetMapping(value = "/admincon")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public String admincon(Model model) {
        model.addAttribute("key1","我的Java世界Security!!!");
        return "index2";
    }

    @GetMapping(value = "/usercon")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public String usercon(Model model) {
        model.addAttribute("key2", "普通");
        return "index3";
    }
}
