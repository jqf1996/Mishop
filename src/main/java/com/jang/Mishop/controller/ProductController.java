package com.jang.Mishop.controller;


import com.github.pagehelper.PageInfo;
import com.jang.Mishop.entity.Product;
import com.jang.Mishop.service.ProductPictureService;
import com.jang.Mishop.service.ProductService;
import com.jang.Mishop.util.ResultMessage;
import com.jang.Mishop.vo.GetProByCatgIdReq;
import com.jang.Mishop.vo.GetProByIdReq;
import com.jang.Mishop.vo.GetProductByPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@RestController
@RequestMapping("/Mishop/product")
@Api(tags = "产品获取接口")
public class ProductController {
     @Autowired
    private ProductService productService;

    @Autowired
    private ResultMessage resultMessage;


    @ApiOperation("根据分类Id来获取接口")

    @PostMapping("/getProductByCatgID")
    public ResultMessage getProductByCatgID(@RequestBody GetProByCatgIdReq getProByCatgIdReq){
        List<Product> list=productService.getProductByCatgId(getProByCatgIdReq);
        resultMessage.success("200","查成功",list);
        return resultMessage;
    }

    @ApiOperation("热度产品接口")

    @PostMapping("/getHotProduct")
    public ResultMessage getHotProduct(){
        List<Product> list=productService.getHotProduct();
        resultMessage.success("200","热度查成功",list);
        return resultMessage;
    }

    @ApiOperation("根据产品id获取数据接口")

    @PostMapping("/getProductById")
    public ResultMessage getProductById(@RequestBody GetProByIdReq getProByIdReq){
        Product product=productService.getProductByid(getProByIdReq);
        resultMessage.success("200","数据查成功",product);
        return resultMessage;
    }

    @ApiOperation("分页获取数据接口")

    @PostMapping("/getProductByPage")
    public ResultMessage getProductByPage(@RequestBody GetProductByPage getProductByPage){
        PageInfo<Product> pageInfo =productService.getProductByPage(getProductByPage);
        resultMessage.success("200","数据查成功",pageInfo);
        return resultMessage;
    }



}

