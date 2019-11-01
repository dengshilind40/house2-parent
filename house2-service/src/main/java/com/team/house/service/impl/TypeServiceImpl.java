package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;

import com.team.house.mapper.TypeMapper;

import com.team.house.service.TypeService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
//查询所有
    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }

    @Override
    public PageInfo<Type> getAllTypeByPage(PageUtil pageinfo) {
        //开启分页
        PageHelper.startPage(pageinfo.getPage(),pageinfo.getRows());
        //查询所有
       TypeExample typeExample=new TypeExample();
        List<com.team.house.entity.Type> list = typeMapper.selectByExample(typeExample);
        //获取分页信息
        PageInfo pageInfo=new PageInfo(list);


        return pageInfo;
    }
     //查询所有
    @Override
    public Integer insertType(Type type) {
       return typeMapper.insertSelective(type);

    }
    //显示要修改的信息  查询一条记录
    @Override
    public Type selectByPrimaryKey(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }
    //修改记录
    @Override
    public int updateByPrimaryKeySelective(Type type) {
      return   typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    @Transactional//事务注解
    public int delType(Integer id) {

        //int i=1/0;
        //2.删除区域
        typeMapper.deleteByPrimaryKey(id);
        return 1;
    }
    //测试事务

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //接口==实现类
        TypeService typeService =(TypeServiceImpl) context.getBean("typeServiceImpl");
        typeService.delType(1012);
        System.out.println("删除成功");
    }
     //批量删除
    @Override
    public int delMoreType(Integer[] ids) {
      return    typeMapper.delMoreType(ids);

    }

}
