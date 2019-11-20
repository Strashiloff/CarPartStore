package com.laba.store.domain;

public class Customer {
  private Long id;
  private String surname;
  private String name;
  private String patronymic;
  private String contact;
  private boolean sex;

  public Customer() {
    this.id = -1L;
    this.surname = "";
    this.name = "";
    this.patronymic = "";
    this.contact = "";
    // false = female, true = male
    this.sex = false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPatronymic() {
    return patronymic;
  }

  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }
}
