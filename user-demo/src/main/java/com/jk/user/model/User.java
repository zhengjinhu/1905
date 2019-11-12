package com.jk.user.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName User
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 17:00
 * @Version V1.0
 **/
@Data
public class User {
    private Integer userId;

    private String userName;

    private String userPwd;

    private Integer userSex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDate;

    private String userImg;

    private String roleIds;

    private Integer roleId;
}
