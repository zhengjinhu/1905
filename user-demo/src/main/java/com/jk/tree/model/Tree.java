package com.jk.tree.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Tree
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 14:53
 * @Version V1.0
 **/
@Data
public class Tree {


    private Integer id;//数节点id

    private String text;//数节点名称

    private String state;//树节点 状态   open 打开(默认)    closed 关闭

    private String url;//自定义数节点url

    private Integer pid;//树节点父id

    private List<Tree> children;

    private String checked;


}