package com.laba.store.domain;

public class Country {
  private Long id;
  private String name;

  public Country() {
    this.id = -1L;
    this.name = "";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
