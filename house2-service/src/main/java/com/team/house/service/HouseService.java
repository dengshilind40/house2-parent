package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;

import com.team.house.utils.HouseUtil;
import com.team.house.utils.PageUtil;

import java.util.List;

public interface HouseService {
       //添加房屋信息、
      int addHouse(House house);
      //查询用户发布的房屋信息  通过用户id
    List<House> getHouseByUserId(Integer uid);
       //查询某条房屋信息   显示修改功能
      House getHouseById(String id);
      //修改出租房
    int updateHouse(House house);
    //删除房屋信息    包含状态 （逻辑删除）
    int  delHouse(String id, Integer state);
    //查询未审核或审核的出租房信息   分页
    PageInfo<House> getHouseByPassState(Integer state, PageUtil pageUtil);
    //更新出租房的审核信息 0未审核 1已审核
  int  updateHousePassState(String id, Integer state);
     //查询所有浏览出租房信息   通过分页  page rows
    PageInfo<House> readHouseNews(HouseUtil houseUtil);
}
