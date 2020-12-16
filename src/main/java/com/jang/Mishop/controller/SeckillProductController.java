package com.jang.Mishop.controller;


import com.jang.Mishop.service.ProductPictureService;
import com.jang.Mishop.service.SeckillProductService;
import com.jang.Mishop.util.ResultMessage;
import com.jang.Mishop.vo.Skill_product.GetSkillProductReq;
import com.jang.Mishop.vo.Skill_product.GetskillProductRes;
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
@RequestMapping("/Mishop/seckill-product")
@Api(tags = "产品秒杀接口")
public class SeckillProductController {
    @Autowired
    private SeckillProductService seckillProductService;

    @Autowired
    private ResultMessage resultMessage;


    @ApiOperation("获取产品图片接口")

    @PostMapping("/GetseckillProduct")
    public ResultMessage GetseckillProduct(@RequestBody GetSkillProductReq getSkillProductReq){
        List<GetskillProductRes> getskillProductRes = seckillProductService.GetProductByTimeId(getSkillProductReq);
        resultMessage.success("200","成功",getskillProductRes);
        return resultMessage;
    }

}

