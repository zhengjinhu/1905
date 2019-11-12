package com.jk.role.controller;

import com.jk.role.model.Role;
import com.jk.role.service.RoleService;
import com.jk.tree.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName RoleController
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 18:38
 * @Version V1.0
 **/
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("queryRole")
    @ResponseBody
    public List<Role> queryRole() {
        return roleService.queryRole();
    }

    @RequestMapping("queryRoleTree")
    @ResponseBody
    public List<Tree> queryRoleTree(Integer id) {
        return roleService.queryRoleTree(id);
    }

    @RequestMapping("saveRolePower")
    @ResponseBody
    public boolean saveRolePower(Integer roleId, Integer[] powerIds) {
        try {
            roleService.saveRolePower(roleId, powerIds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
