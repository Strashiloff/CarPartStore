package com.laba.store.domain;

public class Category {
  private Long id;
  private String name;

  public Category() {
    this.id = -1L;
    this.name = "";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id_category) {
    this.id = id_category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
