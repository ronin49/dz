package com.taxiride.servicediscounts.repo;

import com.taxiride.servicediscounts.repo.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepo extends JpaRepository<Discount, Long> {
}
