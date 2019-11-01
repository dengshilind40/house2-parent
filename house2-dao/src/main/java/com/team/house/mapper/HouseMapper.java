package com.team.house.mapper;


import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.utils.HouseUtil;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //查询房屋信息的方法   通过用户id
            //  List<ExtHouse> getHouseByUsers(Integer usersId);
    List<House> getHouseByUsers(Integer usersId);
      //显示修改功能   通过    查询某条出租房信息
         House   getHouseById(String id);
         //查询未审核或审核的出租房信息
    List<House> getHouseByPassState(Integer state);
    //租户浏览租房信息   有分页插件无需考虑分页
   List<House> readHouseNews(HouseUtil houseUtil);
}