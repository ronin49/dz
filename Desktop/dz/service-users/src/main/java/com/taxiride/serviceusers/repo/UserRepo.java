package com.taxiride.serviceusers.repo;

import com.taxiride.serviceusers.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
