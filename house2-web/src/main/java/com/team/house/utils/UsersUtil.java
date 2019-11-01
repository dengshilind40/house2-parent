package com.team.house.utils;

public class UsersUtil  extends PageUtil{
    private String name;
    private String telephone;
    //继承分页工具   并把查询条件封装成实体

    public UsersUtil() {
    }

    public UsersUtil(String name, String telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    public UsersUtil(int page, int rows, String name, String telephone) {
        super(page, rows);
        this.name = name;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "UsersUtil{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
