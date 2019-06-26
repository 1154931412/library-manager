package com.library.pojo;

import java.util.Date;

public class LendInfo {
    private Long sernum;

    private Integer bookId;

    private Integer readerId;

    private Date lendDate;

    private Date backDate;

    public Long getSernum() {
        return sernum;
    }

    public void setSernum(Long sernum) {
        this.sernum = sernum;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }
}