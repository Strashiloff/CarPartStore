package com.laba.store.domain;

public class Spare_part {
  private Long Id;
  private Long id_type;
  private Long id_section;
  private String name;
  private Double price;
  private String proportions;

  public Spare_part() {
    this.Id = -1L;
    this.id_type = -1L;
    this.id_section = -1L;
    this.name = "";
    this.price = 0.0;
    this.proportions = "";
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_type() {
    return id_type;
  }

  public void setId_type(Long id_type) {
    this.id_type = id_type;
  }

  public Long getId_section() {
    return id_section;
  }

  public void setId_section(Long id_section) {
    this.id_section = id_section;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getProportions() {
    return proportions;
  }

  public void setProportions(String proportions) {
    this.proportions = proportions;
  }
}
