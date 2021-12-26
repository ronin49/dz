package com.taxiride.serviceusers.service.impl;

import com.taxiride.serviceusers.api.dto.Bill;
import com.taxiride.serviceusers.api.dto.Discount;
import com.taxiride.serviceusers.api.dto.User;
import com.taxiride.serviceusers.api.dto.Zapchast;
import com.taxiride.serviceusers.service.BuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class BuyServiceImpl implements BuyService {

  public Bill buy(int userId, int zapchastId) throws AccessDeniedException, HttpClientErrorException {
    RestTemplate rt = new RestTemplate();
    User user = rt.getForObject("http://localhost:9000/users/"+userId,User.class);
    if(user.loggedIn() == false) throw new AccessDeniedException("");
    Discount discount = rt.getForObject("http://localhost:9001/discounts/"+userId,Discount.class);
    Zapchast zapchast = rt.getForObject("http://localhost:9002/zapchasts/"+zapchastId,Zapchast.class);
    Bill bill = new Bill((int)(1.0*zapchast.getPrice()/100*(100-discount.getProcent())));
    return bill;
  }
}
