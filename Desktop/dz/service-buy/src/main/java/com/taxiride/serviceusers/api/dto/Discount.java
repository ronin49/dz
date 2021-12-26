package com.taxiride.serviceusers.api.dto;
public final class Discount {
  private long id;
  private String name;
  private int procent;

  public Discount() {
  }

  public Discount(String name, int procent) {
    this.name = name;
    this.procent = procent;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getProcent() {
    return procent;
  }

  public void setProcent(int procent) {
    this.procent = procent;
  }
}
