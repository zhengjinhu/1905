package com.jk.user.controller;

import com.jk.user.model.User;
import com.jk.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 17:00
 * @Version V1.0
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("queryUser")
    @ResponseBody
    public Map queryUser(int page, int rows) {
        return userService.queryUser(page, rows);
    }

    @RequestMapping("addUser")
    @ResponseBody
    public void addUser(User user) {
        userService.addUser(user);
    }

    /*@RequestMapping("login")
    @ResponseBody
    public String login(User user, HttpServletRequest request) {
        User loginUser = userService.queryUserByUsername(user.getUserName());
        if (loginUser != null) {

            if (loginUser.getUserPwd().equals(user.getUserPwd())) {
                request.getSession().setAttribute("user", loginUser);
                return "success";
            }
            return "pwError";
        }
        return "userError";
    }*/

    /*@RequestMapping("remove")
    public String remove(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "index";
    }*/
    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model){
        String shiroLoginFailure = request.getAttribute("shiroLoginFailure").toString();

        if(UnknownAccountException.class.getName().equals(shiroLoginFailure) || AuthenticationException.class.getName().equals(shiroLoginFailure)){
            model.addAttribute("msg","用户名输入错误 UnknownAccountException");
        }else if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
            model.addAttribute("msg","用户名输入错误 IncorrectCredentialsException");
        }

        return "login";
    }

}
