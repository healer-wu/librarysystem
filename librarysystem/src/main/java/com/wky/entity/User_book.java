package com.wky.entity;

import java.util.Date;

public class User_book {
    private int id;
    private int user;
    private int bookId;
    private Date borrowTime;
    private Date returnTime;

    @Override
    public String toString() {
        return "User_book{" +
                "id=" + id +
                ", user=" + user +
                ", bookId=" + bookId +
                ", borrowTime=" + borrowTime +
                ", returnTime=" + returnTime +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }
}
