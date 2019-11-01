package com.team.house.entity;

public class ExtHouse extends House {
    //继承出租屋类
    private String dname;
    private String tname;
    private String sname;

    @Override
    public String getDname() {
        return dname;
    }

    @Override
    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String getTname() {
        return tname;
    }

    @Override
    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String getSname() {
        return sname;
    }

    @Override
    public void setSname(String sname) {
        this.sname = sname;
    }
}
