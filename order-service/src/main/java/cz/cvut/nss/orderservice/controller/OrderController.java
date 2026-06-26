package cz.cvut.nss.orderservice.controller;

import cz.cvut.nss.orderservice.dto.CreateOrderRequest;
import cz.cvut.nss.orderservice.entity.Order;
import cz.cvut.nss.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns 201 Created instead of 200 OK
    public Order createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
}