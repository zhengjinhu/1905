package com.jk.mapper;

import com.jk.model.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CarMapper
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/5 20:04
 * @Version V1.0
 **/
public interface CarMapper {
    List<Car> queryCarList() throws Exception;

    void addCar(Car car);

    void delCar(String[] split);

    Car queryCarById(Integer id);

    void updCar(Car car);

    void addCarList(List<Car> list);

    List<Car> queryCar(@Param("column") String column);
}
