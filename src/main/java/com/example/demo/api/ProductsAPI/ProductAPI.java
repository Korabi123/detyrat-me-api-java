package com.example.demo.ProductsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductAPI {

    private final List<ProductModel> products = new ArrayList<ProductModel>();

    @GetMapping("/api/products/get")
    public List<ProductModel> getProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) double price,
            @RequestParam(name = "category", required = false) ProductModel.ProductCategory category,
            @RequestParam(name = "id", required = false) String id
    ) {
        return products.stream()
                .filter(product -> (name == null || product.getName().equals(name))
                && (id == null || product.getId().equals(id))
                && (price == product.getPrice())
                && (category == product.getCategory()))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/products/create")
    public List<ProductModel> createProduct(@RequestBody List<ProductModel> newProducts) {
        for (ProductModel product : newProducts) {
            this.products.add(product);
        }
        return products;
    }

    @PatchMapping("/api/products/update")
    public List<ProductModel> updateProduct(
            @RequestParam("id") String id,
            @RequestBody ProductModel updatedProduct
    ) {
        products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .ifPresent(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setCategory(updatedProduct.getCategory());
                });
        return products;
    }

    @DeleteMapping("/api/products/delete")
    public List<ProductModel> deleteProduct(
            @RequestParam("id") String id
    ) {
        products.removeIf(product -> product.getId().equals(id));
        return products;
    }
}
