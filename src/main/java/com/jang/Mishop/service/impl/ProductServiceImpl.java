package com.jang.Mishop.service.impl;

import com.jang.Mishop.entity.Product;
import com.jang.Mishop.mapper.ProductMapper;
import com.jang.Mishop.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
