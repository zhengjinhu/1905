package com.jk.service;

import com.jk.mapper.SolrMapper;
import com.jk.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SolrServiceImpl
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/11 17:02
 * @Version V1.0
 **/
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrMapper solrMapper;


    @Override
    public Integer addCar(Car car) {
        solrMapper.addCar(car);
        return car.getCarId();
    }

    @Override
    public void delCar(String[] split) {
        solrMapper.delCar(split);
    }

    @Override
    public Car queryCarById(Integer id) {
        return solrMapper.queryCarById(id);
    }

    @Override
    public void updCar(Car car) {
        solrMapper.updCar(car);
    }

}
