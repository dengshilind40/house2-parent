package com.team.house.pcontroller;

import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller(value = "typeController2")
public class TypeController {
    @Autowired
    private TypeService typeService;
    //查询所有类型
    @RequestMapping("getAllType")
    @ResponseBody
    public List<Type> getAllType(){
        List<Type> list = typeService.getAllType();
        return list;
    }
}
