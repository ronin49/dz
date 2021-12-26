package com.taxiride.serviceusers.service;

import com.taxiride.serviceusers.api.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface BuyService {
  public Bill buy(int userId, int zapchastId) throws AccessDeniedException, HttpClientErrorException;
}
