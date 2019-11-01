package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;

import com.team.house.utils.UsersUtil;

public interface UsersService {
    //查询管理信息
    PageInfo<Users> getByUsers(UsersUtil usersUtil);
    //注册用户
    int addUser(Users users);
    //验证用户是否已经注册
       int  checkName(String name);
       //登陆验证
        Users    login(String name, String password);
}
