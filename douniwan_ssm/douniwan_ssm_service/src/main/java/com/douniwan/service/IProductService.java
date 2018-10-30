package com.douniwan.service;

import com.douniwan.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll() throws Exception;
}
