package com.jang.Mishop.service;

import com.jang.Mishop.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface CategoryService extends IService<Category> {
        List<Category> getAllCategory();
}
