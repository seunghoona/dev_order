package com.seunghoona.kmong.order.dto;

import com.seunghoona.kmong.order.domain.Money;
import com.seunghoona.kmong.order.domain.Name;
import com.seunghoona.kmong.order.domain.Product;
import lombok.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private long amount;

    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName().getName(),
                product.getAmount().getAmount());
    }

    public static List<ProductResponse> ofList(List<Product> products) {
        return products.stream()
                .map(ProductResponse::of)
                .collect(toList());
    }
}
