package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Car;
import com.jk.service.CarService;
import com.jk.util.ExportExcel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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

    @Reference
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

    @RequestMapping("queryCar")
    @ResponseBody
    public void queryCar(String column,HttpServletResponse response){
        System.err.println(column);
        List<Car> list= new ArrayList<Car>();
        try {
            list = carService.queryCar(column);
            //定义表格的标题
            String title ="1905全体车辆信息";
            //定义列名
            String[] rowName={"编号","名称","日期","价格"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


            //循环数据把数据存放到 List<Object[]>
            for (Car car:list) {
                Object[] obj=new Object[rowName.length];
                if (car.getCarId()!=null && !"".equals(car.getCarId())){
                    obj[0]=car.getCarId();
                }
                if (car.getCarName()!=null && !"".equals(car.getCarName())){
                    obj[1]= car.getCarName();
                }
                if (car.getCarTime()!=null && !"".equals(car.getCarTime())){
                    obj[2]=sdf.format(car.getCarTime());
                }
                if (car.getCarPrice()!=null && !"".equals(car.getCarPrice())){
                    obj[3]=car.getCarPrice();
                }
                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        List<Car> list= new ArrayList<Car>();
        try {
            list = carService.queryCarList();
            //定义表格的标题
            String title ="1905全体车辆信息";
            //定义列名
            String[] rowName={"编号","名称","日期","价格"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


            //循环数据把数据存放到 List<Object[]>
            for (Car car:list) {
                Object[] obj=new Object[rowName.length];
                obj[0]=car.getCarId();
                obj[1]= car.getCarName();
                obj[2]=sdf.format(car.getCarTime());
                obj[3]=car.getCarPrice();

                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";

        //创建list集合接收传递的参数
        List<Car> list= new ArrayList<Car>();

        //上传文件
        try {
            uploadFile(file.getBytes(), filePath, fileName);

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件创建流
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            //创建操作excel的对象   因为xls的文件需要HSSFWorkbook  xlsx需要的XSSFWorkbook 因此直接使用workBook对象就行了
            Workbook workbook= WorkbookFactory.create(fileInputStream) ;
            //通过workbook获得sheet页  sheet有可能有多个
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                //创建sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //根绝sheet创建行  row
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建row对象
                    Row row = sheetAt.getRow(j);

                    //创建对象接收从文件读取的内容
                    Car car=new Car();
                    if( !"".equals(row.getCell(1)) && row.getCell(1)!=null){
                        car.setCarName(row.getCell(1).toString());
                    }
                    if( !"".equals(row.getCell(2)) && row.getCell(2)!=null){
                        car.setCarTime(sdf.parse(row.getCell(2).toString()));
                    }
                    if( !"".equals(row.getCell(3)) && row.getCell(3)!=null){
                        car.setCarPrice(Double.parseDouble(row.getCell(3).toString()));
                    }
                    list.add(car);
                }

            }
            carService.addCarList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "showCar";
    }



    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
