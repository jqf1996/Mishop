package com.jang.Mishop.controller;


import com.jang.Mishop.entity.Product;
import com.jang.Mishop.service.CollectService;
import com.jang.Mishop.util.ResultMessage;
import com.jang.Mishop.vo.Collect.GetcollectByidReq;
import com.jang.Mishop.vo.Collect.GetcollextByidRes;
import com.jang.Mishop.vo.Product.GetProByCatgIdReq;
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
@RequestMapping("/Mishop/collect")
@Api(tags = "收藏获取接口")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    private ResultMessage resultMessage;

    @ApiOperation("根据收藏列表接口")

    @PostMapping("/getcollectByid")
    public ResultMessage getcollectByid(@RequestBody GetcollectByidReq getcollectByidReq){
        List<GetcollextByidRes> collect = collectService.getCollect(getcollectByidReq);
        resultMessage.success("200","查成功",collect);
        return resultMessage;
    }

}

