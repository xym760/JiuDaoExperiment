package com.jiudao.experiment.entity;

import java.io.Serializable;
import java.util.Date;

public class Club implements Serializable {
    private int id;
    private String name;//名称
    private String info;//描述
    private Date createDate;//创建日期
    private int rank;//排名

    public Club(int id, String name, String info, Date createDate, int rank) {
        this.id=id;
        this.name=name;
        this.info=info;
        this.createDate=createDate;
        this.rank=rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", createDate=" + createDate +
                ", rank=" + rank +
                '}';
    }
}
