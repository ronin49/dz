package com.taxiride.servicezapchasts.service;

import com.taxiride.servicezapchasts.repo.model.Zapchast;

import java.util.List;

public interface ZapchastService {
  List<Zapchast> fetchAllZapchasts();
  Zapchast fetchZapchastById(long id) throws IllegalArgumentException;
  long createZapchast(String brand, String model, int price);
  void updateZapchast(long id, String brand, String model, int price) throws IllegalArgumentException;
  void deleteZapchast(long id);
}
