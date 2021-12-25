package com.taxiride.serviceusers.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public final class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private boolean loggedIn;

  public User() {
  }

  public User(String name, boolean loggedIn) {
    this.id = id;
    this.name = name;
    this.loggedIn = loggedIn;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }
}
