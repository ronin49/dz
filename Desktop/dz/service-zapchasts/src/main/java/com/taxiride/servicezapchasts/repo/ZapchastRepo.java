package com.taxiride.servicezapchasts.repo;

import com.taxiride.servicezapchasts.repo.model.Zapchast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZapchastRepo extends JpaRepository<Zapchast, Long> {
}
