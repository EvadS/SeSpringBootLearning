package com.se.product.service.model.payload;

import com.se.product.service.model.PriceRequest;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Product information")
public class ProductRequest {
    private String name;
    private Set<PriceRequest> priceSet = new HashSet<>();
}
