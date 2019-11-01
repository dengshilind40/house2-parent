package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;

import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.UsersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    //查询管理信息   需要分页的信息（page和rows） 和查询条件
    @Override
    public PageInfo<Users> getByUsers(UsersUtil usersUtil) {
        //开启分页
        PageHelper.startPage(usersUtil.getPage(),usersUtil.getRows());
        //查询信息
        //UsersExample生成的实体用来放查询的条件
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria    criteria = usersExample.createCriteria();
        if(usersUtil.getName()!=null){
            criteria.andNameLike("%"+usersUtil.getName()+"%");
        }
        if(usersUtil.getTelephone()!=null){
            criteria.andTelephoneLike("%"+usersUtil.getTelephone()+"%");
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
         PageInfo pageInfo=new PageInfo(list);
        //分页获取信息
        return pageInfo;
    }
    //注册用户

    @Override
    public int addUser(Users users) {
        //使用MD5工具对密码加密
       users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
       //设置isadmin默认为0
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }
    //验证用户是否存在

    @Override
    public int checkName(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //用户名条件
        criteria.andNameEqualTo(name);
        //执行
        List<Users> list = usersMapper.selectByExample(usersExample);

        return list.size();
    }
    //登陆验证

    @Override
    public Users login(String name, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        //密码加密   要和数据库一样
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(usersExample);
        //判断
        if(list.size()==0){
            return null;
        }else
        return list.get(0);
    }
}
