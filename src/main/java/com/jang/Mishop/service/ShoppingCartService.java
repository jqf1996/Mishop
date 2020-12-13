package com.jang.Mishop.service;

import com.jang.Mishop.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jang.Mishop.vo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
        int addchart(CartAddReq cartAddReq); //购物车增加

        List<ChartSearchRes> getchart(ChartSearchReq chartSearchReq);//查询用户购物车

        ChartSearchRes setchart(ShoppingCart shoppingCart);//得到对应产品信息

        int updatecartNum(CartNumReq cartNumReq);

        int deleteChart(CartDelete cartDelete);



}
