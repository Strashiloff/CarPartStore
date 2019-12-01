package com.laba.store.domain;

public class Section {

    private Long id;
    private Long id_stoke;
    private int amount;
    private int capacity;
    private int number;

    public Section() {
        this.id = -1L;
        this.id_stoke = -1L;
        this.amount = 0;
        this.capacity = 0;
        this.number = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_stoke() {
        return id_stoke;
    }

    public void setId_stoke(Long id_stoke) {
        this.id_stoke = id_stoke;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
