package com.jang.Mishop.controller;


import com.jang.Mishop.entity.Carousel;
import com.jang.Mishop.entity.Category;
import com.jang.Mishop.service.CarouselService;
import com.jang.Mishop.service.CategoryService;
import com.jang.Mishop.util.ResultMessage;
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
@RequestMapping("/Mishop/carousel")
@Api(tags = "轮播图接口")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private ResultMessage resultMessage;

    @ApiOperation("轮播图获取列表接口")

    @PostMapping("/getAllBanner")
    public ResultMessage getAllBanner(){
        List<Carousel> allCarousel = carouselService.getAllCarousel();
        resultMessage.success("200","获取成功",allCarousel);
        return resultMessage;
    }
}

