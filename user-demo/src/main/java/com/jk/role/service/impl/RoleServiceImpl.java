package com.jk.role.service.impl;

import com.jk.role.mapper.RoleMapper;
import com.jk.role.model.Role;
import com.jk.role.model.RoleTree;
import com.jk.role.service.RoleService;
import com.jk.tree.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 18:37
 * @Version V1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRole() {
        return roleMapper.queryRole();
    }

    @Override
    public List<Tree> queryRoleTree(Integer id) {
        //查询所有树节点
        List<Tree> plistAll = roleMapper.queryTree();

        //查询此角色所拥有的树节点
        List<RoleTree> rplist = roleMapper.queryRoleTree(id);
        for (Tree tree : plistAll) {
            for (RoleTree roleTree : rplist) {
                if (tree.getId() == roleTree.getTreeId()) {
                    tree.setChecked("true");
                }
            }
        }
        return plistAll;
    }

    @Override
    public void saveRolePower(Integer roleId, Integer[] powerIds) {
        //删除此角色原有的权限管理数据  删除 t_role_power 数据
        roleMapper.delRoleTreeByRoleId(roleId);
        //再讲当前传过来的power数据进行新增 中间表

        List<RoleTree> rpList = new ArrayList<RoleTree>();
        for (Integer powerId : powerIds) {
            RoleTree rp = new RoleTree();
            rp.setRoleId(roleId);
            rp.setTreeId(powerId);
            rpList.add(rp);
        }
        roleMapper.addRoleTree(rpList);
    }
}
