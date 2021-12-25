package com.taxiride.servicezapchasts.service.impl;

import com.taxiride.servicezapchasts.repo.ZapchastRepo;
import com.taxiride.servicezapchasts.repo.model.Zapchast;
import com.taxiride.servicezapchasts.service.ZapchastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class ZapchastServiceImpl implements ZapchastService {

  private final ZapchastRepo zapchastRepo;

  public List<Zapchast> fetchAllZapchasts() {
    return zapchastRepo.findAll();
  }

  public Zapchast fetchZapchastById(long id) throws IllegalArgumentException {
    final Optional<Zapchast> maybeZapchast = zapchastRepo.findById(id);

    if (maybeZapchast.isPresent())
      return maybeZapchast.get();
    else
      throw new IllegalArgumentException("Invalid zapchast ID");
  }

  public long createZapchast(String brand, String model, int price) {
    final Zapchast zapchast = new Zapchast(brand, model, price);
    final Zapchast savedZapchast = zapchastRepo.save(zapchast);

    return savedZapchast.getId();
  }

  public void updateZapchast(long id, String brand, String model, int price) throws IllegalArgumentException {
    final Optional<Zapchast> maybeZapchast = zapchastRepo.findById(id);

    if (maybeZapchast.isEmpty())
      throw new IllegalArgumentException("Invalid zapchast ID");

    final Zapchast zapchast = maybeZapchast.get();
    if (brand != null && !brand.isBlank()) zapchast.setBrand(brand);
    if (model != null && !model.isBlank()) zapchast.setModel(model);
    if (price != 0) zapchast.setPrice(price);
    zapchastRepo.save(zapchast);
  }

  public void deleteZapchast(long id) {
    zapchastRepo.deleteById(id);
  }
}
