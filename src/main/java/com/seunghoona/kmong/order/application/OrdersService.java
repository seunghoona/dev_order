package com.seunghoona.kmong.order.application;

import com.seunghoona.kmong.member.security.utils.MemberContextUtils;
import com.seunghoona.kmong.order.domain.Orders;
import com.seunghoona.kmong.order.domain.OrdersRepo;
import com.seunghoona.kmong.order.domain.Product;
import com.seunghoona.kmong.order.domain.ProductRepo;
import com.seunghoona.kmong.order.dto.OrdersRequest;
import com.seunghoona.kmong.order.dto.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepo ordersRepo;
    private final ProductRepo productRepo;

    public OrdersResponse order(OrdersRequest orderRequest) {
        Product product = productRepo.findById(orderRequest.getProductId())
                .orElseThrow(NoResultException::new);
        Orders savedOrders = ordersRepo.save(Orders.create(product, MemberContextUtils.getEntity()));
        return OrdersResponse.of(savedOrders);
    }

    public List<OrdersResponse> list() {
        Long memberId = MemberContextUtils.getMemberId();
        return OrdersResponse.ofList(ordersRepo.findByMemberId(memberId));
    }

}
