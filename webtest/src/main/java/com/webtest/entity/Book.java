package com.webtest.entity;

public class Book {
    private Integer id;
    private String name;
    private String auth;
    private Integer bookId;
    private Integer count;

    private User user;

    private Borrow borrow;

    public Book() {
    }

    public Book(Integer id, String name, String auth, Integer bookId, Integer count, User user, Borrow borrow) {
        this.id = id;
        this.name = name;
        this.auth = auth;
        this.bookId = bookId;
        this.count = count;
        this.user = user;
        this.borrow = borrow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", auth='" + auth + '\'' +
                ", bookId=" + bookId +
                ", count=" + count +
                ", user=" + user +
                ", borrow=" + borrow +
                '}';
    }
}
