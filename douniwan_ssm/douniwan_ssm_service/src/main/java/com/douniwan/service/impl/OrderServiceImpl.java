package com.douniwan.service.impl;

import com.douniwan.dao.IOrderDao;
import com.douniwan.domain.Order;
import com.douniwan.service.IOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    /**
     * 查询所有订单信息
     * @return
     * @throws Exception
     * @param page
     * @param size
     */
    @Override
    public List<Order> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }
}
