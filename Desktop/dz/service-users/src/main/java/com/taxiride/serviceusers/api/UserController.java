package com.taxiride.serviceusers.api;

import com.taxiride.serviceusers.repo.model.User;
import com.taxiride.serviceusers.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public final class UserController {

  private final UserServiceImpl userServiceImpl;

  @GetMapping
  public ResponseEntity<List<User>> index() {
    final List<User> users = userServiceImpl.fetchAllUsers();

    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> showById(@PathVariable long id) {
    try {
      final User user = userServiceImpl.fetchUserById(id);

      return ResponseEntity.ok(user);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.taxiride.serviceusers.api.dto.User user) {
    final String name = user.name();
    final boolean loggedIn = user.loggedIn();

    final long userId = userServiceImpl.createUser(name, loggedIn);
    final String userUri = String.format("/users/%d", userId);

    return ResponseEntity.created(URI.create(userUri)).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.taxiride.serviceusers.api.dto.User user) {
    final String name = user.name();
    final boolean loggedIn = user.loggedIn();

    try {
      userServiceImpl.updateUser(id, name, loggedIn);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    userServiceImpl.deleteUser(id);

    return ResponseEntity.noContent().build();
  }
}
