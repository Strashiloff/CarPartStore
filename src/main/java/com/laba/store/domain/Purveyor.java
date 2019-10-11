package com.laba.store.domain;

public class Purveyor {
  private Long id;
  private Long id_category;
  private Long id_country;
  private String warranty;
  private String name;
  private String adress;

  public Purveyor() {
    this.id = -1L;
    this.id_category = -1L;
    this.id_country = -1L;
    this.warranty = "";
    this.name = "";
    this.adress = "";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId_category() {
    return id_category;
  }

  public void setId_category(Long id_category) {
    this.id_category = id_category;
  }

  public Long getId_country() {
    return id_country;
  }

  public void setId_country(Long id_country) {
    this.id_country = id_country;
  }

  public String getWarranty() {
    return warranty;
  }

  public void setWarranty(String warranty) {
    this.warranty = warranty;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }
}
