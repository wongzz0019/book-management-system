package com.webtest.vo;

public class UpdateUser {
    private Integer id;
    private String username;
    private String stuname;
    private Integer sex;
    private String password;
    private Integer stuid;
    private String classes;

    public UpdateUser() {
    }

    public UpdateUser(Integer id, String username, String stuname, Integer sex, String password, Integer stuid, String classes) {
        this.id = id;
        this.username = username;
        this.stuname = stuname;
        this.sex = sex;
        this.password = password;
        this.stuid = stuid;
        this.classes = classes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "UpdateUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", stuname='" + stuname + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", stuid=" + stuid +
                ", classes='" + classes + '\'' +
                '}';
    }
}
