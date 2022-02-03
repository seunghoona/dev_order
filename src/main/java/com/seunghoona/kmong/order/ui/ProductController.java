package com.seunghoona.kmong.order.ui;


import com.seunghoona.kmong.order.application.ProductService;
import com.seunghoona.kmong.order.dto.ProductRequest;
import com.seunghoona.kmong.order.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest createProduct) {
        ProductResponse product = productService.create(createProduct);
        return ResponseEntity.created(URI.create("/products/" + product.getId())).body(product);
    }
}
