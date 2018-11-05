package com.douniwan.dao;

import com.douniwan.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITravellerDao {
    //根据id查询会员信息 中间表查的的值应该有多个 所以 要in才不会报错
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id})")
    List<Traveller> findTravellerByOrderId(String id) throws Exception;
}
