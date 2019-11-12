package com.jk.mapper;

import com.jk.model.Car;

/**
 * @ClassName SolrMapper
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/11 17:02
 * @Version V1.0
 **/
public interface SolrMapper {

    void addCar(Car car);

    void delCar(String[] split);

    Car queryCarById(Integer id);

    void updCar(Car car);
}
