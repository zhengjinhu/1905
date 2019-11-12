/**
 * Copyright (C), 2019, 金科教育
 * FileName: Car
 * Author:   zyl
 * Date:     2019/11/4 10:27
 * History:
 * zyl          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zyl
 * @create 2019/11/4
 * @since 1.0.0
 */
@Data
public class Car implements Serializable{

    private static final long serialVersionUID = 8506191501202450805L;

    private Integer carId;

    private String carName;

    private String carTime;

    private Double carPrice;

    private String carShow;

    private Double minPrice;

    private Double maxPrice;
}
