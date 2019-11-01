package com.team.house.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;

import com.team.house.utils.HouseUtil;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }
       //查询房屋信息
    @Override
    public List<House> getHouseByUserId(Integer uid) {
        return houseMapper.getHouseByUsers(uid);
    }
    //查询某条房屋信息  修改显示功能

    @Override
    public House getHouseById(String id) {
        return houseMapper.getHouseById(id);
    }
    //修改出租房
    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }
         //逻辑删除房屋信息   就是修改房屋的状态信息
    @Override
    public int delHouse(String id, Integer state) {
        //实体类
        House house=new House();
          //设置房屋id
        house.setId(id);
        //设置出租屋状态是否删除
        house.setIsdel(state);
        //调用业务
      return   houseMapper.updateByPrimaryKeySelective(house);

    }
    //查询未审核或审核的出租房信息   分页
    @Override
    public PageInfo<House> getHouseByPassState(Integer state, PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询所有
        List<House> list = houseMapper.getHouseByPassState(state);
        //分页获取信息

        return new PageInfo<House>(list);
    }
    //修改出租房的审核信息 0未审核 1已审核

    @Override
    public int updateHousePassState(String id, Integer state) {
        //实体
        House house=new House();
        //设置id
        house.setId(id);
        //设置状态
        house.setIspass(state);
        //调用dao
        return houseMapper.updateByPrimaryKeySelective(house);
    }
    //实现查询所有浏览出租房信息   通过分页  page rows
    @Override
    public PageInfo<House> readHouseNews(HouseUtil houseUtil) {
        //开启分页
        PageHelper.startPage(houseUtil.getPage(),houseUtil.getRows());
        //调用dao  查询
        List<House> list = houseMapper.readHouseNews(houseUtil);
        //获取分页信息
        PageInfo<House> pageInfo=new PageInfo<House>(list);
        return pageInfo;
    }
}
