package com.taxiride.servicediscounts.api;

import com.taxiride.servicediscounts.repo.model.Discount;
import com.taxiride.servicediscounts.service.impl.DiscountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/discounts")
@RestController
public final class DiscountController {

  private final DiscountServiceImpl discountServiceImpl;

  @GetMapping
  public ResponseEntity<List<Discount>> index() {
    final List<Discount> discounts = discountServiceImpl.fetchAllDiscounts();

    return ResponseEntity.ok(discounts);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Discount> showById(@PathVariable long id) {
    try {
      final Discount discount = discountServiceImpl.fetchDiscountById(id);

      return ResponseEntity.ok(discount);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.taxiride.servicediscounts.api.dto.Discount discount) {
    final String name = discount.name();
    final int procent = discount.procent();
    final long discountId = discountServiceImpl.createDiscount(name, procent);
    final String discountUri = String.format("/discounts/%d", discountId);

    return ResponseEntity.created(URI.create(discountUri)).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.taxiride.servicediscounts.api.dto.Discount discount) {
    final String name = discount.name();
    final int procent = discount.procent();

    try {
      discountServiceImpl.updateDiscount(id, name, procent);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    discountServiceImpl.deleteDiscount(id);

    return ResponseEntity.noContent().build();
  }
}
