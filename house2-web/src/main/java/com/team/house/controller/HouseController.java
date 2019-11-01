package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;

import com.team.house.service.HouseService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;
    //未审核的
    @RequestMapping("getHouseNotPass")//easyui框架中的datagrid自动传page,rows
    @ResponseBody
    public Map<String,Object> getHouseNoPass(PageUtil pageUtil){
        //调用业务
        //0表示未审核
        PageInfo<House> pageInfo = houseService.getHouseByPassState(0,pageUtil);
        //返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    //未审核的去审核
    @RequestMapping("goHousePass")
    @ResponseBody
      public Map<String, Object> goHousePass(String id){
        //1表示已审核
        int i = houseService.updateHousePassState(id,1);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }
    //已审核的
    @RequestMapping("getHousePass")//easyui框架中的datagrid自动传page,rows
    @ResponseBody
    public Map<String,Object> getHousePass(PageUtil pageUtil){
        //调用业务
        //0表示未审核
        PageInfo<House> pageInfo = houseService.getHouseByPassState(1, pageUtil);
        //返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    //已审核的取消审核
    @RequestMapping("goHouseNotPass")
    @ResponseBody
    public Map<String,Object> goHouseNotPass(String id){
        //1表示通过审核
        int i = houseService.updateHousePassState(id,0);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }

}
