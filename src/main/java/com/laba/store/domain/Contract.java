package com.laba.store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Contract {

  private Long Id;
  private String member_one;
  private String member_two;
  private String body;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;

  public Contract() {
    Id = -1L;
    this.member_one = "";
    this.member_two = "";
    this.body = "";
    this.date = null;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getMember_one() {
    return member_one;
  }

  public void setMember_one(String member_one) {
    this.member_one = member_one;
  }

  public String getMember_two() {
    return member_two;
  }

  public void setMember_two(String member_two) {
    this.member_two = member_two;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
