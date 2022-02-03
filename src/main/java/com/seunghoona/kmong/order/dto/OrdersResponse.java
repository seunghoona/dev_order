package com.seunghoona.kmong.order.dto;

import com.seunghoona.kmong.order.domain.OrderStatus;
import com.seunghoona.kmong.order.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {

    private String orderNo;
    private String productName;
    private Long amount;
    private OrderStatus orderStatus;
    private LocalDateTime orderDateTime;

    public static OrdersResponse of(Orders savedOrders) {
        return new OrdersResponse(savedOrders.getOrderNo(),
                savedOrders.getProduct().getName().getName(),
                savedOrders.getProduct().getAmount().getAmount(),
                savedOrders.getOrderStatus(),
                savedOrders.getCreatedDate());
    }

    public static List<OrdersResponse> ofList(List<Orders> orders) {
        return orders.stream()
                .map(OrdersResponse::of)
                .collect(Collectors.toList());
    }
}
