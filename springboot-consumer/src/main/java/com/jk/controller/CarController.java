package com.jk.controller;

import com.jk.model.Car;
import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName CarController
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/5 20:11
 * @Version V1.0
 **/
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("toShowCar")
    public String toShowCar(){
        return "showCar";
    }
    @RequestMapping("toAddCar")
    public String toAddCar(){
        return "addCar";
    }
    @RequestMapping("queryCarList")
    @ResponseBody
    public List<Car> queryCarList(){
        List<Car> list = new ArrayList<>();
        try {
            list = carService.queryCarList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @RequestMapping("addCar")
    @ResponseBody
    public void addCar(Car car){
        carService.addCar(car);
    }
    @RequestMapping("delCar")
    @ResponseBody
    public void delCar(String ids){
        carService.delCar(ids);
    }
    @RequestMapping("queryCarById")
    @ResponseBody
    public Car queryCarById(Integer id){
        Car car = carService.queryCarById(id);
        return car;
    }
    @RequestMapping("updCar")
    @ResponseBody
    public void updCar(Car car){
        carService.updCar(car);
    }

}
