package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.CarMapper;
import com.jk.model.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CarServiceImpl
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/5 20:02
 * @Version V1.0
 **/
@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> queryCarList() {
        List<Car> list = new ArrayList<Car>();
        try {
            list = carMapper.queryCarList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addCar(Car car) {
        carMapper.addCar(car);
    }

    @Override
    public void delCar(String ids) {
        String[] split = ids.split(",");
        carMapper.delCar(split);
    }

    @Override
    public Car queryCarById(Integer id) {
        return carMapper.queryCarById(id);
    }

    @Override
    public void updCar(Car car) {
        carMapper.updCar(car);
    }

    @Override
    public void addCarList(List<Car> list) {
        carMapper.addCarList(list);
    }

    @Override
    public List<Car> queryCar(String column) throws Exception {
        return carMapper.queryCar(column);
    }
}
