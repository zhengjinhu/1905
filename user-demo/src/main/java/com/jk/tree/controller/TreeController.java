package com.jk.tree.controller;

import com.jk.tree.model.Tree;
import com.jk.tree.service.TreeService;
import com.jk.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName TreeController
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 14:52
 * @Version V1.0
 **/
@Controller
@RequestMapping("tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @RequestMapping("queryTreeList")
    @ResponseBody
    public List<Tree> queryTreeList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        return treeService.queryTreeList(userId);
    }

}
