package com.taxiride.servicediscounts.service;

import com.taxiride.servicediscounts.repo.model.Discount;

import java.util.List;

public interface DiscountService {
  List<Discount> fetchAllDiscounts();
  Discount fetchDiscountById(long id) throws IllegalArgumentException;
  long createDiscount(String name, int procent);
  void updateDiscount(long id, String name, int procent) throws IllegalArgumentException;
  void deleteDiscount(long id);
}
