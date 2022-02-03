package com.seunghoona.kmong.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findByMemberId(Long memberId);
}
