/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CarService
 * Author:   zyl
 * Date:     2019/11/4 10:33
 * History:
 */
package com.jk.service;

import com.jk.model.Car;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zyl
 * @create 2019/11/4
 * @since 1.0.0
 */
public interface CarService {

    List<Map<String, Object>> queryCar();


    List<Map<String, Object>> queryCarAll();
}
