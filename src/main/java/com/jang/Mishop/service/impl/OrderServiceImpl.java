package com.jang.Mishop.service.impl;

import com.jang.Mishop.entity.Order;
import com.jang.Mishop.mapper.OrderMapper;
import com.jang.Mishop.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
