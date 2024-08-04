package com.nhn.corp.ext.orderservice.order.web;

import com.nhn.corp.ext.orderservice.config.PolarProperties;
import com.nhn.corp.ext.orderservice.order.domain.Order;
import com.nhn.corp.ext.orderservice.order.domain.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final PolarProperties polarProperties;

    public OrderController(OrderService orderService, PolarProperties polarProperties) {
        this.orderService = orderService;
        this.polarProperties = polarProperties;
    }

    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
    }

    @GetMapping("/test")
    public String configTest(){
        return polarProperties.getGreeting();
    }
}
