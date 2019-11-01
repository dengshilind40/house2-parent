package com.team.house.mapper;

import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
    //删除街道  根据区域id
    int deleteStreetByDistrict(Integer id);
    //批量删除   参数可以为数组或者String类型的
       int   delMoreDistrict(Integer[] ids);

}