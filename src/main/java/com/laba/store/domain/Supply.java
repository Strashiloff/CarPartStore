package com.laba.store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Supply {

  private Long Id;
  private Long id_contract;
  private Long id_purveyor;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private Double tax;

  public Supply() {
    this.Id = -1L;
    this.id_contract = -1L;
    this.id_purveyor = -1L;
    this.date = null;
    this.tax = 0.0;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_contract() {
    return id_contract;
  }

  public void setId_contract(Long id_contract) {
    this.id_contract = id_contract;
  }

  public Long getId_purveyor() {
    return id_purveyor;
  }

  public void setId_purveyor(Long id_purveyor) {
    this.id_purveyor = id_purveyor;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Double getTax() {
    return tax;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }
}
