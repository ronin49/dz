package com.taxiride.serviceusers.service.impl;

import com.taxiride.serviceusers.repo.UserRepo;
import com.taxiride.serviceusers.repo.model.User;
import com.taxiride.serviceusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  public List<User> fetchAllUsers() {
    return userRepo.findAll();
  }

  public User fetchUserById(long id) throws IllegalArgumentException {
    final Optional<User> maybeUser = userRepo.findById(id);

    if (maybeUser.isPresent())
      return maybeUser.get();
    else
      throw new IllegalArgumentException("Invalid user ID");
  }

  public long createUser(String name, boolean loggedIn) {
    final User user = new User(name, loggedIn);
    final User savedZapchast = userRepo.save(user);

    return savedZapchast.getId();
  }

  public void updateUser(long id, String name, boolean loggedIn) throws IllegalArgumentException {
    final Optional<User> maybeUser = userRepo.findById(id);

    if (maybeUser.isEmpty())
      throw new IllegalArgumentException("Invalid user ID");

    final User user = maybeUser.get();
    if (name != null && !name.isBlank()) user.setName(name);
    user.setLoggedIn(loggedIn);
    userRepo.save(user);
  }

  public void deleteUser(long id) {
    userRepo.deleteById(id);
  }
}
