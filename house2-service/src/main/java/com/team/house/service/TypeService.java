package com.team.house.service;

import com.github.pagehelper.PageInfo;

import com.team.house.entity.Type;
import com.team.house.utils.PageUtil;

import java.util.List;

public interface TypeService {
      //查询所有
     //功能  +方法
     List<Type> getAllType();
     //查询所有并支持分页
     PageInfo getAllTypeByPage(PageUtil pageinfo);
      //添加区域信息
      Integer insertType(Type Type);
      //显示要修改的信息  查询一条记录
      Type selectByPrimaryKey(Integer id);
     //修改记录
     int updateByPrimaryKeySelective(Type type);
      //删除类型信息
    public int delType(Integer id);
    //批量删除租房类型
    int   delMoreType(Integer[] ids);

}
