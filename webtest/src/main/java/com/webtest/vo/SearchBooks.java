package com.webtest.vo;

//该类用来接收前端返回的需要搜索的book的关键值
public class SearchBooks {
    private Integer bookId;
    private String name;

    public SearchBooks() {
    }

    public SearchBooks(Integer bookId, String name) {
        this.bookId = bookId;
        this.name = name;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SearchBooks{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                '}';
    }
}
