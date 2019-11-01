package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;

import com.team.house.utils.PageUtil;



import java.util.List;

public interface DistrictService {
      //查询所有区域
     //功能  +方法
     List<District> getAllDistrict();
     //查询所有并支持分页
     PageInfo getAllDistrictByPage(PageUtil pageinfo);
      //添加区域信息
      Integer insertDistrict(District district);
      //显示要修改的信息  查询一条记录
     District selectByPrimaryKey(Integer id);
     //修改记录
     int updateByPrimaryKeySelective(District district);
      //删除区域信息(包含街道)
    public int delDistrict(Integer id);
    //批量删除区域
    int   delMoreDistrict(Integer[] ids);

}
