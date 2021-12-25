package com.taxiride.servicediscounts.service.impl;

import com.taxiride.servicediscounts.repo.DiscountRepo;
import com.taxiride.servicediscounts.repo.model.Discount;
import com.taxiride.servicediscounts.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class DiscountServiceImpl implements DiscountService {

  private final DiscountRepo discountRepo;

  public List<Discount> fetchAllDiscounts() {
    return discountRepo.findAll();
  }

  public Discount fetchDiscountById(long id) throws IllegalArgumentException {
    final Optional<Discount> maybeDiscount = discountRepo.findById(id);

    if (maybeDiscount.isPresent())
      return maybeDiscount.get();
    else
      throw new IllegalArgumentException("Invalid discount ID");
  }

  public long createDiscount(String name, int procent) {
    final Discount discount = new Discount(name, procent);
    final Discount savedDiscount = discountRepo.save(discount);

    return savedDiscount.getId();
  }

  public void updateDiscount(long id, String name, int procent) throws IllegalArgumentException {
    final Optional<Discount> maybeDiscount = discountRepo.findById(id);

    if (maybeDiscount.isEmpty())
      throw new IllegalArgumentException("Invalid discount ID");

    final Discount discount = maybeDiscount.get();
    if (name != null && !name.isBlank()) discount.setName(name);
    if (procent != 0) discount.setProcent(procent);
    discountRepo.save(discount);
  }

  public void deleteDiscount(long id) {
    discountRepo.deleteById(id);
  }
}
