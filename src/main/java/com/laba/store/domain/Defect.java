package com.laba.store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Defect {

  private Long Id;
  private Long id_buy;
  private Long id_list;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private int amount;

  public Defect() {
    Id = -1L;
    this.id_buy = -1L;
    this.id_list = -1L;
    this.date = null;
    this.amount = 0;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_buy() {
    return id_buy;
  }

  public void setId_buy(Long id_buy) {
    this.id_buy = id_buy;
  }

  public Long getId_list() {
    return id_list;
  }

  public void setId_list(Long id_list) {
    this.id_list = id_list;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
