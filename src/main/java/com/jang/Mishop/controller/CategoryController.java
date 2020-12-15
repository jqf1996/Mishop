package com.jang.Mishop.controller;


import com.jang.Mishop.entity.Category;
import com.jang.Mishop.mapper.CategoryMapper;
import com.jang.Mishop.service.CategoryService;
import com.jang.Mishop.service.CollectService;
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
@RequestMapping("/Mishop/category")
@Api(tags = "分类接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResultMessage resultMessage;

    @ApiOperation("分类获取列表接口")

    @PostMapping("/getAllcategory")
    public ResultMessage getAllcategory(){
        List<Category> allCategory = categoryService.getAllCategory();
        resultMessage.success("200","获取成功",allCategory);
        return resultMessage;
    }
}

