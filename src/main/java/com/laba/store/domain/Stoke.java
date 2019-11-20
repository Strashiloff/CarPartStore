package com.laba.store.domain;

public class Stoke {

    private int Id;
    private String location;
    private Integer count;

    public Stoke() {
        this.Id = -1;
        this.location = "";
        this.count = 0;
    }

    public Stoke(int id, String location, Integer count) {
        this.Id = id;
        this.location = location;
        this.count = count;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
