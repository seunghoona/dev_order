package com.seunghoona.kmong.order.ui;

import com.seunghoona.kmong.order.application.OrdersService;
import com.seunghoona.kmong.order.dto.OrdersRequest;
import com.seunghoona.kmong.order.dto.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrdersResponse> order(@RequestBody OrdersRequest orderRequest) {
        OrdersResponse orders = ordersService.order(orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + orders.getOrderNo())).body(orders);
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> list() {
        return ResponseEntity.ok().body(ordersService.list());
    }
}
