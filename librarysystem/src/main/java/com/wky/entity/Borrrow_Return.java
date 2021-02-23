package com.wky.entity;

public class Borrrow_Return {
    private String author;
    private String name;
    private String press;
    private String true_name;
    private String telephone;

    @Override
    public String toString() {
        return "Borrrow_Return{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", press='" + press + '\'' +
                ", true_name='" + true_name + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getPress() {
        return press;
    }

    public String getTrue_name() {
        return true_name;
    }
}
