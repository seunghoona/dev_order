package com.seunghoona.kmong.order.dto;

import com.seunghoona.kmong.order.domain.Product;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProduct {
    private String name;
    private long amount;

    public Product toProduct() {
        return Product.create(name, amount);
    }
}
