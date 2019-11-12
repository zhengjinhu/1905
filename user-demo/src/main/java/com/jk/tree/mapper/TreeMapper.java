package com.jk.tree.mapper;

import com.jk.tree.model.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName TreeMapper
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 14:52
 * @Version V1.0
 **/
public interface TreeMapper {
    List<Tree> queryTree(@Param("id") int id, @Param("userId") Integer userId);
}
