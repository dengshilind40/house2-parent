package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;


import com.team.house.service.DistrictService;

import com.team.house.utils.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RestController=@Controller+@RequestMapping
@RequestMapping("/admin/")

public class DistrictController {
      @Autowired
    private DistrictService districtService;

      @RequestMapping("getAllDistrict")
      @ResponseBody
    public List<District> getAllDistrict(){
          return districtService.getAllDistrict();
      }
//分页
    @RequestMapping("getAllDistrictByPage")
    @ResponseBody
    public Map<String,Object> getAllDistrictByPage(PageUtil pageUtil){
        PageInfo pageInfo = districtService.getAllDistrictByPage(pageUtil);
        //用map封装返回的数据
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
            /*
            * 添加区域信息
            * */
            @RequestMapping("/addDistrict")
          @ResponseBody//"result":"0"
            public  Map<String, Object>     addDistrict(District district){
              Integer integer = districtService.insertDistrict(district);
              //用map封装返回的数据
                 Map<String,Object> map=new HashMap<>();
                 map.put("result",integer);
                 return map;

          }
        //显示修改信息  根据id 查询一条记录
    @RequestMapping("selectByOne")
    @ResponseBody
           public    District selectByOne(Integer id){
                District district=districtService.selectByPrimaryKey(id);
                return district;
           }


     //修改记录
    @RequestMapping("updateDistrict")
    @ResponseBody
           public     Map<String,Object>   updateDistrict(District district){
        int i = districtService.updateByPrimaryKeySelective(district);
        //把返回的数据封装到map中
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);//自动将对象转化为json

        return  map;
    }
            //删除区域
    @RequestMapping("deleteDistrict")
    @ResponseBody
           public  Map<String,Object>    deleteDistrict(Integer id){
               int i = districtService.delDistrict(id);
               //将返回的数据封装到map中
               Map<String,Object> map=new HashMap<>();
              // return "{\"result\":"+flag+"}";  //手工拼的json
               map.put("result",i);//自动转换为json数据
               return map;
           }
        //批量删除区域
     @RequestMapping("delMoreDistrict")
     @ResponseBody
            public   Map<String,Object>   delMoreDistrict(String ids){
                //把string类型转换为int 的包装类型
                  String[] list=ids.split(",");
                  Integer[] arr=new Integer[list.length];
                      for(int i=1;i<list.length;i++){
                          arr[i] = Integer.parseInt(list[i]);
                      }
                      //调用业务的方法
         int i = districtService.delMoreDistrict(arr);
         Map<String,Object> map=new HashMap<>();
                      map.put("result",i);
                      return  map;

            }


}
