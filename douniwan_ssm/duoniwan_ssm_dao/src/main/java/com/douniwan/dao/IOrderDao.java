package com.douniwan.dao;

import com.douniwan.domain.Order;
import com.douniwan.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrderDao {
      @Select("select * from orders")
      @Results(id="ordersMap",value={
              @Result(id=true ,property = "id" ,column = "id"),
              @Result(property = "orderNum" ,column = "orderNum"),
              @Result(property = "orderTime" ,column = "orderTime"),
              @Result(property = "orderStatus" ,column = "orderStatus"),
              @Result(property = "peopleCount" ,column = "peopleCount"),
              @Result(property = "payType" ,column = "payType"),
              @Result(property = "orderDesc" ,column = "orderDesc"),
              @Result(property = "product" ,column = "productId",one=@One(select = "com.douniwan.dao.IProductDao.findById") ,
                      javaType = Product.class )
      })
      List<Order> findAll() throws Exception;
}
