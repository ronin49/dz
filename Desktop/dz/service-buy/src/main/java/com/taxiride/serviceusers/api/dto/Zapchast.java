package com.taxiride.serviceusers.api.dto;
public final class Zapchast {
  private long id;
  private String brand;
  private String model;
  private int price;
  public Zapchast() {
  }
  public Zapchast(String brand, String model, int price) {
    this.brand = brand;
    this.model = model;
    this.price = price;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
