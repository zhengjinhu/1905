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

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zyl
 * @create 2019/11/4
 * @since 1.0.0
 */
public interface CarService {
    List<Car> queryCarList()throws Exception;

    void addCar(Car car);

    void delCar(String ids);

    Car queryCarById(Integer id);

    void updCar(Car car);

    void addCarList(List<Car> list);

    List<Car> queryCar(String column) throws Exception;
}
