package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;

import com.team.house.service.UsersService;


import com.team.house.utils.UsersUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

@Controller
//@RestController=@Controller+@RequestMapping
@RequestMapping("/admin/")

public class UsersController {
      @Autowired
    private UsersService usersService;
      //分页模糊查询
      @RequestMapping("getByUsers")
      @ResponseBody
      public Map<String,Object> getByUsers(UsersUtil usersUtil){
          PageInfo<Users> pageInfo = usersService.getByUsers(usersUtil);
          Map<String,Object> map=new HashMap<>();
          map.put("rows",pageInfo.getList());
          map.put("total",pageInfo.getTotal());
          return   map;
    }
}
