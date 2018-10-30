package com.douniwan.dao;

import com.douniwan.domain.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("productDao")
public interface IProductDao {
  @Select("select * from product")
  List<Product> findAll() throws Exception;
}
