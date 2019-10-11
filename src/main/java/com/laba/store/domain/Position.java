package com.laba.store.domain;

public class Position {

  private Long Id;
  private Long id_supply;
  private Long id_spare_part;
  private int amount;
  private Double price;

  public Position() {
    Id = -1L;
    this.id_supply = -1L;
    this.id_spare_part = -1L;
    this.amount = -1;
    this.price = 0.0;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_supply() {
    return id_supply;
  }

  public void setId_supply(Long id_supply) {
    this.id_supply = id_supply;
  }

  public Long getId_spare_part() {
    return id_spare_part;
  }

  public void setId_spare_part(Long id_spare_part) {
    this.id_spare_part = id_spare_part;
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
