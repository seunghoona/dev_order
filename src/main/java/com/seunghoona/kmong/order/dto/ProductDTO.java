package com.seunghoona.kmong.order.dto;

import com.seunghoona.kmong.order.domain.Money;
import com.seunghoona.kmong.order.domain.Name;
import com.seunghoona.kmong.order.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Builder
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private Name name;
    private Money amount;

    public static ProductDTO of(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getAmount());
    }

    public static List<ProductDTO> ofList(List<Product> products) {
        return products.stream()
                .map(ProductDTO::of)
                .collect(toList());
    }
}
