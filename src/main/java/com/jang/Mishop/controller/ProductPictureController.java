package com.jang.Mishop.controller;


import com.jang.Mishop.entity.ProductPicture;
import com.jang.Mishop.service.ProductPictureService;
import com.jang.Mishop.util.ResultMessage;
import com.jang.Mishop.vo.ProductPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/Mishop/product-picture")
@Api(tags = "产品图片接口")
public class ProductPictureController {

    @Autowired
    private ProductPictureService pictureService;

    @Autowired
    private ResultMessage resultMessage;


    @ApiOperation("获取产品图片接口")

    @PostMapping("/getpicture")

    public ResultMessage getPicture(ProductPic productPic){
        List<ProductPicture> list=pictureService.GetProductPic(productPic);
        resultMessage.success("200","产品图片获取成功");
        return resultMessage;
    }
}

