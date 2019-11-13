package com.jk.controller;

import com.jk.model.Car;
import com.jk.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @ClassName SolrController
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/11 17:02
 * @Version V1.0
 **/
@Controller
public class SolrController {

    @Autowired
    private SolrService solrService;

    @Autowired
    private SolrClient client;

    @RequestMapping("toShowCar")
    public String toShowCar(){
        return "showCar";
    }
    @RequestMapping("toAddCar")
    public String toAddCar(){
        return "addCar";
    }

    @RequestMapping("queryCarById")
    @ResponseBody
    public Car queryCarById(Integer id) {
        Car car = solrService.queryCarById(id);
        System.out.println(11111);

        System.out.println(0000);
        System.out.println(4444444);
        System.out.println(3333);
        return car;
    }


    @RequestMapping("delCar")
    @ResponseBody
    public void delCar(String ids){
        String[] split = ids.split(",");
        solrService.delCar(split);

        try {
            for(int i=0;i<split.length;i++){
                client.deleteById("core1",split[i]);
                client.commit("core1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("updCar")
    @ResponseBody
    public void updCar(Car car){
        solrService.updCar(car);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", car.getCarId());
            doc.setField("carName", car.getCarName());
            doc.setField("carTime", car.getCarTime());
            doc.setField("carPrice", car.getCarPrice());
            doc.setField("carShow", car.getCarShow());


            client.add("core1", doc);
            client.commit("core1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("addCar")
    @ResponseBody
    public void addCar(Car car){
        Integer carId = solrService.addCar(car);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", carId);
            doc.setField("carName", car.getCarName());
            doc.setField("carTime", car.getCarTime());
            doc.setField("carPrice", car.getCarPrice());
            doc.setField("carShow", car.getCarShow());


            client.add("core1", doc);
            client.commit("core1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("queryCar")
    @ResponseBody
    public Map<String,Object> queryCar(Car car ,Integer page,Integer rows){

        Map<String, Object> map1=new HashMap<>();

        try {
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            if(car.getCarName()!=null && !"".equals(car.getCarName())){
                params.set("q", car.getCarName());
            }else {
                params.set("q", "*:*");
            }
            //过滤条件
            if (car.getMinPrice()!=null && !"".equals(car.getMinPrice()) && car.getMaxPrice()!=null && !"".equals(car.getMaxPrice())){
                params.set("fq", "carPrice:["+car.getMinPrice()+" TO "+car.getMaxPrice()+"]");
            }

            //排序
            params.addSort("carPrice", SolrQuery.ORDER.desc);

            //分页
            if(page==null){
                params.setStart(0);
            }else{
                params.setStart((page-1)*rows);
            }
            if(rows==null){
                params.setRows(5);
            }else {
                params.setRows(rows);
            }

            //默认域
            params.set("df", "carName");

            //只查询指定域
           // params.set("fl", "id,product_title,product_price");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("carName");
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            QueryResponse queryResponse = client.query("core1",params);

            SolrDocumentList results = queryResponse.getResults();

            long numFound = results.getNumFound();

            System.out.println(numFound);
            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            List<Car> list1 =new ArrayList<>();
            for (SolrDocument result : results) {
                Car car1=new Car();
                String highFile="";

                Map<String, List<String>> map = highlight.get(result.get("id"));
                List<String> list = map.get("carName");
                if(list==null){
                    highFile= result.get("carName").toString();
                }else {
                    highFile=list.get(0);
                }

                car1.setCarId(Integer.parseInt(result.get("id").toString()));
                car1.setCarShow(result.get("carShow").toString());
                car1.setCarTime(result.get("carTime").toString());
                car1.setCarPrice(Double.parseDouble(result.get("carPrice").toString()));
                car1.setCarName(highFile);

                list1.add(car1);

            }
            map1.put("total",numFound);
            map1.put("rows",list1);
            return map1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
