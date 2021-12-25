package com.taxiride.serviceusers.service;

import com.taxiride.serviceusers.repo.model.User;

import java.util.List;

public interface UserService {
  List<User> fetchAllUsers();
  User fetchUserById(long id) throws IllegalArgumentException;
  long createUser(String name, boolean loggedIn);
  void updateUser(long id, String name, boolean loggedIn) throws IllegalArgumentException;
  void deleteUser(long id);
}
