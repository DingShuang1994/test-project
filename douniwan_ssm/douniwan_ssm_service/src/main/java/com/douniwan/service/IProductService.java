package com.douniwan.service;

import com.douniwan.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加产品
     * @param product
     * @throws Exception
     */
    void save(Product product) throws Exception;
}
