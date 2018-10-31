package com.douniwan.dao;

import com.douniwan.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {
    //根据id查询会员信息
    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;
}
