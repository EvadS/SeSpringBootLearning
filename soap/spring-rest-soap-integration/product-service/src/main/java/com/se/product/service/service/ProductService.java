package com.se.product.service.service;

import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest product);
}
