package com.webtest.entity;

import java.util.Date;

public class Borrow {
    private Integer bookId;
    private Integer stuId;
    private Date borrowDate;        //创建时间

    public Borrow() {
    }

    public Borrow(Integer bookId, Integer stuId, Date borrowDate) {
        this.bookId = bookId;
        this.stuId = stuId;
        this.borrowDate = borrowDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "bookId=" + bookId +
                ", stuId=" + stuId +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
