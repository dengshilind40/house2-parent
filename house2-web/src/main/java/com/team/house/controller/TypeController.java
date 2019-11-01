package com.team.house.controller;

import com.github.pagehelper.PageInfo;

import com.team.house.entity.Type;

import com.team.house.service.TypeService;

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

public class TypeController {
      @Autowired
    private TypeService typeService;

      @RequestMapping("getAllType")
      @ResponseBody
    public List<Type> getAllType(){

          return typeService.getAllType();
      }
//分页
    @RequestMapping("getAllTypeByPage")
    @ResponseBody
    public Map<String,Object> getAllTypeByPage(PageUtil pageUtil){
        PageInfo pageInfo = typeService.getAllTypeByPage(pageUtil);
        //用map封装返回的数据
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
            /*
            * 添加区域信息
            *
            * */
            @RequestMapping("addType")
          @ResponseBody//"result":"0"
            public  Map<String, Object>     addType(Type type){
              Integer integer = typeService.insertType(type);
              //用map封装返回的数据
                 Map<String,Object> map=new HashMap<>();
                 map.put("result",integer);
                 return map;

          }
        //显示修改信息  根据id 查询一条记录
    @RequestMapping("selectByOneType")
    @ResponseBody
           public    Type selectByOneType(Integer id){
        Type type=typeService.selectByPrimaryKey(id);
                return type;
           }


     //修改记录
    @RequestMapping("updateType")
    @ResponseBody
           public     Map<String,Object>   updateType(Type type){
        int i = typeService.updateByPrimaryKeySelective(type);
        //把返回的数据封装到map中
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);//自动将对象转化为json

        return  map;
    }
            //删除区域
    @RequestMapping("deleteType")
    @ResponseBody
           public  Map<String,Object>    deleteType(Integer id){
               int i = typeService.delType(id);
        //               //将返回的数据封装到map中
               Map<String,Object> map=new HashMap<>();
              // return "{\"result\":"+flag+"}";  //手工拼的json
               map.put("result",i);//自动转换为json数据
               return map;
           }
        //批量删除区域
     @RequestMapping("delMoreType")
     @ResponseBody
            public   Map<String,Object>   delMoreType(String ids){
                //把string类型转换为int 的包装类型
                  String[] list=ids.split(",");
                  Integer[] arr=new Integer[list.length];
                      for(int i=0;i<list.length;i++){
                          arr[i] = Integer.parseInt(list[i]);
                      }
                      //调用业务的方法
         int i = typeService.delMoreType(arr);
         Map<String,Object> map=new HashMap<>();
                      map.put("result",i);
                      return  map;

            }


}
