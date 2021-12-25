package com.taxiride.serviceusers.api;

import com.taxiride.serviceusers.api.dto.Bill;
import com.taxiride.serviceusers.api.dto.Discount;
import com.taxiride.serviceusers.api.dto.Receipt;
import com.taxiride.serviceusers.api.dto.Zapchast;
import com.taxiride.serviceusers.repo.model.User;
import com.taxiride.serviceusers.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/buy")
@RestController
public final class BuyController {
    @PostMapping
    public ResponseEntity<Bill> buy(@RequestBody Receipt receipt) throws AccessDeniedException {
        RestTemplate rt = new RestTemplate();
        try {
            int userId = receipt.user();
            int zapchastId = receipt.zapchast();
            User user = rt.getForObject("http://localhost:9000/users/"+userId,User.class);
            if(user.isLoggedIn() == false) throw new AccessDeniedException("");
            Discount discount = rt.getForObject("http://localhost:9001/discounts/"+userId,Discount.class);
            Zapchast zapchast = rt.getForObject("http://localhost:9002/zapchasts/"+zapchastId,Zapchast.class);
            Bill bill = new Bill((int)(1.0*zapchast.getPrice()/100*(100-discount.getProcent())));
            return ResponseEntity.ok(bill);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
