package com.seunghoona.kmong.order.dto;

import com.seunghoona.kmong.order.domain.Product;
import lombok.*;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "필수입니다.")
    private String name;

    @PositiveOrZero(message = "0 또는 0보다 작을 수 없습니다.")
    private long amount;

    public Product toProduct() {
        return Product.of(name, amount);
    }
}
