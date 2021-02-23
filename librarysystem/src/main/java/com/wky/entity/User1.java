package com.wky.entity;


public class User1 {
    private Integer id;
    private String trueName;
    private String telephone;

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", trueName='" + trueName + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getId() {
        return id;
    }

    public String getTrueName() {
        return trueName;
    }
}
