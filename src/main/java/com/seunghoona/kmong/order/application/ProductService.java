package com.seunghoona.kmong.order.application;

import com.seunghoona.kmong.order.domain.Product;
import com.seunghoona.kmong.order.domain.ProductRepo;
import com.seunghoona.kmong.order.dto.CreateProduct;
import com.seunghoona.kmong.order.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public ProductResponse create(CreateProduct createProduct) {
        Product savedProduct = productRepo.save(createProduct.toProduct());
        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> list() {
        return ProductResponse.ofList(productRepo.findAll());
    }
}
