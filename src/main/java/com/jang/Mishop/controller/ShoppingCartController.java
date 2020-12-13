package com.jang.Mishop.controller;


import com.jang.Mishop.entity.ShoppingCart;
import com.jang.Mishop.mapper.ShoppingCartMapper;
import com.jang.Mishop.service.ShoppingCartService;
import com.jang.Mishop.util.ResultMessage;
import com.jang.Mishop.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/Mishop/shopping-cart")
@Api(tags = "购物车接口")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private RedisTemplate redisTemplate;


    @ApiOperation("添加购物车接口")
    @PostMapping("/addchart")

    public ResultMessage addchart(@RequestBody CartAddReq cartAddReq){
        int i = shoppingCartService.addchart(cartAddReq);
        resultMessage.success("200","添加成功");
        return resultMessage;
    }

    @ApiOperation("查询用户购物车接口")
    @PostMapping("/getchart")

    public ResultMessage getchart(@RequestBody ChartSearchReq chartSearchReq){
        List<ChartSearchRes> getchart = shoppingCartService.getchart(chartSearchReq);
        resultMessage.success("200","查询成功",getchart);
        return resultMessage;
    }


    @ApiOperation("更新购物车数量接口")
    @PostMapping("/updatechartnum")
    public ResultMessage updatechartnum(@RequestBody CartNumReq cartNumReq){
        shoppingCartService.updatecartNum(cartNumReq);
        resultMessage.success("200","更新购物车数量成功");
        return resultMessage;
    }

    @ApiOperation("删除购物车接口")
    @PostMapping("/deletecart")
    public ResultMessage deletecart(@RequestBody CartDelete cartDelete){
        shoppingCartService.deleteChart(cartDelete);
        resultMessage.success("200","删除购物车数量成功");
        return resultMessage;
    }


}

