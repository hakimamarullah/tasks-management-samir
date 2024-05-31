package com.micro.fintech.service;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/28/2024 11:25 PM
@Last Modified 5/28/2024 11:25 PM
Version 1.0
*/

import com.micro.fintech.model.Product;
import com.micro.fintech.model.dto.PagedResponse;
import com.micro.fintech.model.dto.ProductDTO;
import com.micro.fintech.model.response.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ApiResponse<PagedResponse<Product>> getAllProducts(Pageable pageable);

    ApiResponse<Product> getProductById(Integer id);

    ApiResponse<Void> deleteProductById(Integer id);


    ApiResponse<Void> updateProductById(ProductDTO product);

    ApiResponse<Integer> saveBatch(List<ProductDTO> productDTOS);

    ApiResponse<Void> save(ProductDTO productDTO);

    ApiResponse<List<Product>> searchByName(String name);
}
