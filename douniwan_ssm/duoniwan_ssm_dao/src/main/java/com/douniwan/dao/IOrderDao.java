package com.douniwan.dao;

import com.douniwan.domain.Member;
import com.douniwan.domain.Order;
import com.douniwan.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrderDao {
      //查询所有订单信息
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

      //根据id查询订单信息
      @Select("select * from orders where id = #{id}")
      @Results(value={
              @Result(id=true ,property = "id" ,column = "id"),
              @Result(property = "orderNum" ,column = "orderNum"),
              @Result(property = "orderTime" ,column = "orderTime"),
              @Result(property = "orderStatus" ,column = "orderStatus"),
              @Result(property = "peopleCount" ,column = "peopleCount"),
              @Result(property = "payType" ,column = "payType"),
              @Result(property = "orderDesc" ,column = "orderDesc"),
              @Result(property = "product" ,column = "productId",one=@One(select = "com.douniwan.dao.IProductDao.findById") ,
                      javaType = Product.class ),
              @Result(property = "member",column = "memberId" , one=@One(select = "com.douniwan.dao.IMemberDao.findById"),javaType = Member.class),
              @Result(property = "travellers" , column = "id" ,many=@Many(select="com.douniwan.dao.ITravellerDao.findTravellerByOrderId"),javaType = List.class)

      })
      Order findById(String id) throws Exception;

}
