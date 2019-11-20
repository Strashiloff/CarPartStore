package com.laba.store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Buy {
  private Long Id;
  private Long id_request;
  private boolean completed;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;

  public Buy() {
    Id = -1L;
    this.id_request = -1L;
    this.completed = false;
    this.date = null;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getId_request() {
    return id_request;
  }

  public void setId_request(Long id_request) {
    this.id_request = id_request;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
