package com.laba.store.domain;

public class Stoke {

    private Long Id;
    private String location;
    private Integer count;

    public Stoke() {
        this.Id = -1L;
        this.location = "";
        this.count = 0;
    }

    public Stoke(Long id, String location, Integer count) {
        this.Id = id;
        this.location = location;
        this.count = count;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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
