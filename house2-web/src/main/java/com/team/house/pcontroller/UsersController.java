package com.team.house.pcontroller;


import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller(value = "usersController2")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("reg")
    public String reg(Users users){
        int i = usersService.addUser(users);
        if(i>0)
            return "/login";//登陆页面
        else
            return "regs";//注册页面
    }
    //验证用户名是否存在
    @RequestMapping("checkName")
    @ResponseBody//{"result":1或0}
    public   String  checkName(String name){
        int i = usersService.checkName(name);
        return "{\"result\":"+i+"}";
    }
    //登陆验证
    @RequestMapping("login")

    public String login(String name, String password, HttpSession session){
        Users users= usersService.login(name, password);
        if(users!=null){
            //使用session保存登入的人
            session.setAttribute("usersInfo",users);
            //设置session的有效期 秒
            session.setMaxInactiveInterval(600);
            //重定向跳转到房屋信息的方法中
            return "redirect:getHouseByUser.do";//后台管理
        }else {
            return "login";//登陆页面
        }
    }

}
