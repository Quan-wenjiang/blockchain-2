package com.eqqq.demo.controller;

import com.eqqq.demo.bean.*;
import com.eqqq.demo.mapper.goodsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class goodsController {
    @Resource
    goodsMapper Mapper;

    @GetMapping("/getgood/{good}")
    public goodbyid getgood(@PathVariable("good") String goodid) {
        seller seller = Mapper.getsellers(goodid);
        goods goods = Mapper.getgood(goodid);
        List<filelist> list1 = new ArrayList<>();
        List<filelist> list2 = new ArrayList<>();
        filelist fileList=new filelist();
        fileList.name=goods.SellerID;
        fileList.url="http://localhost:8181/assets/images/"+goods.SellerID+".jpg";
        list1.add(fileList);
        filelist fileList2=new filelist();
        fileList2.name=seller.SellerName;
        fileList2.url="http://localhost:8181/assets/images/"+seller.SellerName+".jpg";
        list2.add(fileList2);
        goodbyid goodbyid=new goodbyid();
        goodbyid.name=goods.SellerID;
        goodbyid.quantity=goods.ReviewNum;
        goodbyid.score1=goods.ProductScore;
        goodbyid.fileList=list1;
        goodbyid.fileList2=list2;
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            System.exit(0);//退出程序
        }
        return goodbyid;
    }

    @GetMapping("/allgoods")
    public List<product> getgoods() {
        Collection<goods> goods = Mapper.getgoods();
        List<product> list1 = new ArrayList<>();
        for (goods good:goods){
            product product=new product();
            product.id=good.SellerID;
            product.name=good.SellerID;
            product.id=good.SellerID;
            product.url="http://localhost:8181/assets/images/"+good.SellerID+".jpg";
            product.score1=Integer.parseInt(good.ProductScore);
            product.score2=Integer.parseInt(good.ProductScore);
            product.sales="已售"+good.ReviewNum;
            list1.add(product);
        }
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            System.exit(0);//退出程序
        }
        return list1;
    }



    @PostMapping("/add")
    public String addgoods(@RequestBody add add) {
        System.out.println(add);
        Mapper.insertgoods(add.SellerID,"0","0", new Date());
        Mapper.insertseller(add.SellerName," "," ",add.SellerID,new Date());


        return"success";
    }



}
