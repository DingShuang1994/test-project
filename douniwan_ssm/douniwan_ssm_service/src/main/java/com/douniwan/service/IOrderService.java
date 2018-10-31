package com.douniwan.service;

import com.douniwan.domain.Order;

import java.util.List;

public interface IOrderService {
    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     * @param page
     * @param size
     */
    List<Order> findAll(int page, int size) throws Exception;

//    /**
//     * 添加产品
//     * @param order
//     * @throws Exception
//     */
//    void save(Order order) throws Exception;
}
