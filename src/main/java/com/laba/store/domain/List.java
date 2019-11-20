package com.laba.store.domain;

public class List {

  private Long Id;
  private Long id_request;
  private Long id_position;
  private int amount;
  private Double price;

  public List() {
    Id = -1L;
    this.id_request = -1L;
    this.id_position = -1L;
    this.amount = -1;
    this.price = 0.0;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_request() {
    return id_request;
  }

  public void setId_request(Long id_request) {
    this.id_request = id_request;
  }

  public Long getId_position() {
    return id_position;
  }

  public void setId_position(Long id_position) {
    this.id_position = id_position;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
