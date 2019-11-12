package com.jk.role.mapper;

import com.jk.role.model.Role;
import com.jk.role.model.RoleTree;
import com.jk.tree.model.Tree;

import java.util.List;

/**
 * @ClassName RoleMappe
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 18:37
 * @Version V1.0
 **/
public interface RoleMapper {
    List<Role> queryRole();

    List<Tree> queryTree();

    List<RoleTree> queryRoleTree(Integer id);

    void delRoleTreeByRoleId(Integer roleId);

    void addRoleTree(List<RoleTree> rpList);
}
