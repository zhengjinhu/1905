package com.jk.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName PageController
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 17:04
 * @Version V1.0
 **/
@Controller
public class PageController {

    @RequestMapping("toMain")
    public String toMain() {
        return "main";
    }

    @RequestMapping("toShowUser")
    public String toShowUser() {
        return "showUser";
    }

    @RequestMapping("toAddUser")
    public String toAddUser() {
        return "addUser";
    }

    @RequestMapping("toShowRole")
    public String toShowRole() {
        return "showRole";
    }

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index";
    }
}
