package com.jang.Mishop.mapper;

import com.jang.Mishop.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@Resource
@Repository
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
