package com.team.house.pcontroller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;

import com.team.house.service.HouseService;
import com.team.house.utils.HouseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller(value = "houseController2")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("addHouse")
    public String addHouse(House house, HttpSession session, @RequestParam(value = "image", required = false) MultipartFile image) {
        //文件上传的名字
        String filename = image.getOriginalFilename();
        //截取文件名后缀（扩展名）
        String s = filename.substring(filename.lastIndexOf("."));
        //生成文件名字
        String bh = System.currentTimeMillis() + "";
        //文件名字加后缀名（图片）
        String name = bh + s;
        //文件路径
        String path = "E:\\images\\" + name;
        File file= new File(path);

        //上传
        try {
            image.transferTo(file);
            //用业务将数据保存到数据库
            //设置编号
            house.setId(bh);
            //设置图片
            house.setPath(name);
            //设置用户编号
            Users users =(Users) session.getAttribute("usersInfo");
            house.setUserId(users.getId());
            //保存
            int i = houseService.addHouse(house);
            if(i>0) return "fabu";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    //查询房屋信息
    @RequestMapping("getHouseByUser")
    public String getHouseByUser(HttpSession session, Model model){
        //设置用户编号
        Users users =(Users) session.getAttribute("usersInfo");

        List<House> list = houseService.getHouseByUserId(users.getId());
          model.addAttribute("list",list);

        System.out.println(list);
          return "guanli";
    }
    //查询某一条房屋信息  显示修改功能
    @RequestMapping("showHouse")
    public String showFaBu(String id,Model model){
        House house = houseService.getHouseById(id);
        model.addAttribute("house",house);
        System.out.println(house);
        return "upfabu";
    }
    //修改出租房
    @RequestMapping("updateHouse")
    public String updateHouse(House house,String oldimage,@RequestParam(value = "image",required = false)MultipartFile image){
        try {
            //根据用户是否选择图片来看要不要上传图片
            if(!image.isEmpty()){
                //1.上传文件
                String name=image.getOriginalFilename();  //文件名
                String extName=name.substring(name.lastIndexOf("."));//扩展名
                String bh=System.currentTimeMillis()+"";
                String filename=bh+extName;
                String path="E:\\images\\"+filename;
                File file=new File(path);
                image.transferTo(file);   //上传
                //设置图片
                house.setPath(filename);
            }

            //2.调用业务将数据保存到数据库
            houseService.updateHouse(house); //保存
            if (!image.isEmpty()){
                //删除旧的图片
                File file=new File("E:\\images\\"+oldimage);
                file.delete();
            }
            return "redirect:getHouseByUser.do";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";

    }
    //逻辑删除房屋信息  状态为1表示删除 0表示未删除
    @RequestMapping("delHouse")
     @ResponseBody
     public Map<String,Object> delHouse(String id,Integer state){
        int i = houseService.delHouse(id, 1);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }
     //查询用户浏览的租房信息
    @RequestMapping("searchHouse")
    public String searchHouse(HouseUtil houseUtil,Model model){
        //设置默认当前页和页大小
        houseUtil.setRows(3);
        PageInfo<House> house = houseService.readHouseNews(houseUtil);
        System.out.println(house);
        //填充信息到作用域
        model.addAttribute("house",house);
        //把查询条件给前端
        model.addAttribute("houseUtil",houseUtil);
        return "list";
    }



}