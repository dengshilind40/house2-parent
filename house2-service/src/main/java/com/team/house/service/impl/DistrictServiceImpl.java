package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;


import com.team.house.mapper.DistrictMapper;

import com.team.house.service.DistrictService;
import com.team.house.utils.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
//查询所有区域
    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

    @Override
    public PageInfo<District> getAllDistrictByPage(PageUtil pageinfo) {
        //开启分页
        PageHelper.startPage(pageinfo.getPage(),pageinfo.getRows());
        //查询所有
        DistrictExample districtExample=new DistrictExample();
        List<District> districtList = districtMapper.selectByExample(districtExample);
        //获取分页信息
        PageInfo pageInfo=new PageInfo(districtList);


        return pageInfo;
    }
     //查询所有
    @Override
    public Integer insertDistrict(District district) {
       return districtMapper.insertSelective(district);

    }
    //显示要修改的信息  查询一条记录
    @Override
    public District selectByPrimaryKey(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }
    //修改记录

    @Override
    public int updateByPrimaryKeySelective(District district) {
      return   districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional//事务注解
    public int delDistrict(Integer id) {
        //1.通过区域道编号删除街     编写dao层
        districtMapper.deleteStreetByDistrict(id);
          //int i=1/0;
        //2.删除区域
        districtMapper.deleteByPrimaryKey(id);
        return 1;
    }
    //测试事务

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //接口==实现类
        DistrictService  districtService =(DistrictServiceImpl) context.getBean("districtServiceImpl");
            districtService.delDistrict(1012);
        System.out.println("删除成功");
    }
     //批量删除
    @Override
    public int delMoreDistrict(Integer[] ids) {
      return    districtMapper.delMoreDistrict(ids);

    }

}
