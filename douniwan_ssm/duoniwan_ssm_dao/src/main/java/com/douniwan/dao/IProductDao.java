package com.douniwan.dao;

import com.douniwan.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("productDao")
public interface IProductDao {
  /**
   * 查询所有产品信息
   * @return 产品信息集合
   * @throws Exception
   */
  @Select("select * from product")
  List<Product> findAll() throws Exception;

  //添加
  @Insert("insert into product(productnum, productname, cityname, departuretime, productprice, " +
          "productdesc, productstatus) values(#{productNum},#{productName},#{cityName},#{departureTime}," +
          "#{productPrice},#{productDesc},#{productStatus})")
  void save(Product product);
  @Select("select * from product where id = #{id}")
  Product findById(String id) throws Exception;

}
