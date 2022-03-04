package com.webtest.vo;

public class SearchStudent {
    private Integer stuid;
    private String stuname;

    public SearchStudent() {
    }

    public SearchStudent(Integer stuid, String stuname) {
        this.stuid = stuid;
        this.stuname = stuname;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    @Override
    public String toString() {
        return "SearchStudent{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                '}';
    }
}
