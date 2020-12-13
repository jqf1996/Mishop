package com.jang.Mishop.service.impl;

import com.jang.Mishop.entity.Category;
import com.jang.Mishop.mapper.CategoryMapper;
import com.jang.Mishop.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
