package com.taxiride.servicezapchasts.api;

import com.taxiride.servicezapchasts.repo.model.Zapchast;
import com.taxiride.servicezapchasts.service.impl.ZapchastServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/zapchasts")
@RestController
public final class ZapchastController {

  private final ZapchastServiceImpl zapchastServiceImpl;

  @GetMapping
  public ResponseEntity<List<Zapchast>> index() {
    final List<Zapchast> zapchasts = zapchastServiceImpl.fetchAllZapchasts();

    return ResponseEntity.ok(zapchasts);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Zapchast> showById(@PathVariable long id) {
    try {
      final Zapchast zapchast = zapchastServiceImpl.fetchZapchastById(id);

      return ResponseEntity.ok(zapchast);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.taxiride.servicezapchasts.api.dto.Zapchast zapchast) {
    final String brand = zapchast.brand();
    final String model = zapchast.model();
    final int price = zapchast.price();
    final long zapchastId = zapchastServiceImpl.createZapchast(brand, model, price);
    final String zapchastUri = String.format("/zapchasts/%d", zapchastId);

    return ResponseEntity.created(URI.create(zapchastUri)).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.taxiride.servicezapchasts.api.dto.Zapchast zapchast) {
    final String brand = zapchast.brand();
    final String model = zapchast.model();
    final int price = zapchast.price();

    try {
      zapchastServiceImpl.updateZapchast(id, brand, model, price);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    zapchastServiceImpl.deleteZapchast(id);

    return ResponseEntity.noContent().build();
  }
}
