package com.laba.store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Request {

  private Long Id;
  private Long id_user;
  private Long id_customer;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime period;

  public Request() {
    Id = -1L;
    this.id_user = -1L;
    this.id_customer = -1L;
    this.date = null;
    this.period = null;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_user() {
    return id_user;
  }

  public void setId_user(Long id_user) {
    this.id_user = id_user;
  }

  public Long getId_customer() {
    return id_customer;
  }

  public void setId_customer(Long id_customer) {
    this.id_customer = id_customer;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public LocalDateTime getPeriod() {
    return period;
  }

  public void setPeriod(LocalDateTime period) {
    this.period = period;
  }
}
