package com.jk.tree.service.impl;

import com.jk.tree.mapper.TreeMapper;
import com.jk.tree.model.Tree;
import com.jk.tree.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TreeServiceImpl
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 14:53
 * @Version V1.0
 **/
@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;


    //同步树：从根节点开始，查询所有树节点
    //递归树
    @Override
    public List<Tree> queryTreeList(Integer userId) {
        //首先查询根节点
        List<Tree> list = this.queryNode(0, userId);
        return list;
    }

    //递归方法
    public List<Tree> queryNode(int id, Integer userId) {
        List<Tree> list = treeMapper.queryTree(id, userId);
        //通过根节点查询子节点
        for (Tree t : list) {
            //查询每一个跟节点的子节点
            List<Tree> zlist = this.queryNode(t.getId(), userId);
            if (zlist.size() > 0) {
                t.setState("closed");
                t.setChildren(zlist);
            }
        }
        return list;
    }
}
