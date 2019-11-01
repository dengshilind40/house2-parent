package com.team.house.pcontroller;

import com.team.house.entity.District;


import com.team.house.service.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    //查询所有类型
    @RequestMapping("findAllDistrict")
    @ResponseBody
    public List<District> findAllDistrict(){
      return   districtService.getAllDistrict();

    }
}
