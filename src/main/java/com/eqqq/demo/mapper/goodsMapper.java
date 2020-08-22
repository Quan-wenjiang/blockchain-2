package com.eqqq.demo.mapper;


import com.eqqq.demo.bean.goods;
import com.eqqq.demo.bean.seller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface goodsMapper {
    @Select("select * from goods")
    public Collection<goods> getgoods();
    @Select("select * from goods where SellerID= #{good}")
    public goods getgood(@Param("good") String good);

    @Select("select * from seller where Goods= #{good}")
    public seller getsellers(@Param("good") String good);



    @Insert("insert into goods values(#{SellerID}, #{ProductScore}, #{ReviewNum}, #{Time})")
    public int insertgoods(@Param("SellerID")String sellerID, @Param("ProductScore") String ProductScore,@Param("ReviewNum") String ReviewNum ,@Param("Time") Date Time);
//
//    @Select("select * from covid")
//    public List<Covid> getAll();
}


