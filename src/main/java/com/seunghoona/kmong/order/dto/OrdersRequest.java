package com.seunghoona.kmong.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequest {

    @NotNull(message = "필수 입니다.")
    private Long productId;
}
