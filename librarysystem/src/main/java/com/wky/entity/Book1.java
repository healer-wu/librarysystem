package com.wky.entity;


public class Book1 {
    private Integer id;
    private String author;
    private String name;
    private String press;

    @Override
    public String toString() {
        return "Book1{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", press='" + press + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }



    public String getPress() {
        return press;
    }
}
