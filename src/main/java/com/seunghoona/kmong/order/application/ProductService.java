package com.seunghoona.kmong.order.application;

import com.seunghoona.kmong.order.domain.ProductRepo;
import com.seunghoona.kmong.order.dto.CreateProduct;
import com.seunghoona.kmong.order.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public void create(CreateProduct createProduct) {
        productRepo.save(createProduct.toProduct());
    }

    public List<ProductDTO> list() {
        return ProductDTO.ofList(productRepo.findAll());
    }
}
