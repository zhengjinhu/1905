package com.jk.role.service;

import com.jk.role.model.Role;
import com.jk.tree.model.Tree;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 18:36
 * @Version V1.0
 **/
public interface RoleService {
    List<Role> queryRole();

    List<Tree> queryRoleTree(Integer id);

    void saveRolePower(Integer roleId, Integer[] powerIds);
}
