package com.jk.tree.service;

import com.jk.tree.model.Tree;

import java.util.List;

/**
 * @ClassName TreeService
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 14:53
 * @Version V1.0
 **/
public interface TreeService {
    List<Tree> queryTreeList(Integer userId);
}
