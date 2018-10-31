package com.douniwan.service.impl;

import com.douniwan.dao.IProductDao;
import com.douniwan.domain.Product;
import com.douniwan.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    //查询所有产品信息
    @Override
    public List<Product> findAll() throws Exception {
        //只要在要分页的语句前配置一条语句
        //PageHelper.startPage(1,5);
        return productDao.findAll();
    }

    //保存一条产品信息
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
