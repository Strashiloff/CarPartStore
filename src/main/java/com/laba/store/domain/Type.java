package com.laba.store.domain;

public class Type {
  private Long Id;
  private String name;

  public Type() {
    this.Id = -1L;
    this.name = "";
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
