package com.taxiride.serviceusers.api;

import com.taxiride.serviceusers.api.dto.Bill;
import com.taxiride.serviceusers.api.dto.Discount;
import com.taxiride.serviceusers.api.dto.Receipt;
import com.taxiride.serviceusers.api.dto.Zapchast;
import com.taxiride.serviceusers.service.BuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@RequestMapping("/buy")
@RestController
public final class BuyController {
    final BuyService buyService;
    @PostMapping
    public ResponseEntity<Bill> buy(@RequestBody Receipt receipt) throws AccessDeniedException {
        RestTemplate rt = new RestTemplate();
        try {
            int userId = receipt.user();
            int zapchastId = receipt.zapchast();
            return ResponseEntity.ok(buyService.buy(userId,zapchastId));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
