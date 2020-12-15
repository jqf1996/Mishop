package com.jang.Mishop.service.impl;

import com.jang.Mishop.entity.Category;
import com.jang.Mishop.mapper.CategoryMapper;
import com.jang.Mishop.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = categoryMapper.selectList(null);
        return categories;
    }
}
