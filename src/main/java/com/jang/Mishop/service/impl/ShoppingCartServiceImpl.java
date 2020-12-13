package com.jang.Mishop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jang.Mishop.entity.Product;
import com.jang.Mishop.entity.ShoppingCart;
import com.jang.Mishop.entity.User;
import com.jang.Mishop.exception.ExceptionEnum;
import com.jang.Mishop.exception.XmException;
import com.jang.Mishop.mapper.ProductMapper;
import com.jang.Mishop.mapper.ShoppingCartMapper;
import com.jang.Mishop.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jang.Mishop.util.ResultMessage;
import com.jang.Mishop.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private ResultMessage resultMessage;

    @Override
    public int addchart(CartAddReq cartAddReq) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(cartAddReq.getUser_id());
        cart.setProductId(cartAddReq.getProduct_id());

        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(cart);

        ShoppingCart one = shoppingCartMapper.selectOne(queryWrapper);//selectone 找到一个实体
        if (one != null) {
            if (one.getNum() >= 5) { // TODO 这里默认设为5 后期再动态修改
                throw new XmException(ExceptionEnum.ADD_CART_NUM_UPPER);
            }
            one.setNum(one.getNum()+1);
            shoppingCartMapper.updateById(one);
            return 0;
        }
        else {
            cart.setNum(1);
            shoppingCartMapper.insert(cart);
        }

        return 1 ;
    }

    @Override
    public List<ChartSearchRes> getchart(ChartSearchReq chartSearchReq) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(chartSearchReq.getUser_id());
        List<ChartSearchRes> list=new ArrayList<>();
        List<ShoppingCart> shoppingCartList=null;
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(cart);

        shoppingCartList=shoppingCartMapper.selectList(queryWrapper);

        // for each 语句  类型 对象：集合或数组等
        for (ShoppingCart c : shoppingCartList) {
            list.add(setchart(c));
        }
        return list;
    }

    @Override
    public ChartSearchRes setchart(ShoppingCart shoppingCart) {
        Product product = productMapper.selectById(shoppingCart.getProductId());

        ChartSearchRes res = new ChartSearchRes();

        res.setProductId(shoppingCart.getProductId());//产品id
        res.setPrice(product.getProductPrice());//产品价格
        res.setProductName(product.getProductName());//产品名称
        res.setProductImg(product.getProductPicture());//产品图片
        res.setNum(shoppingCart.getNum());
        res.setMaxNum(5);
        res.setCheck(false);
        return res;


    }

    @Override
    public int updatecartNum(CartNumReq cartNumReq) {
        ShoppingCart cart = new ShoppingCart();
        cart.setNum(cartNumReq.getProduct_num());
        cart.setProductId(cartNumReq.getProduct_id());
        cart.setUserId(cartNumReq.getUser_id());


            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",cart.getUserId())
                        .eq("product_id",cart.getProductId());
            int s=shoppingCartMapper.update(cart,queryWrapper);
            if (s!=1){
                throw new XmException(ExceptionEnum.GET_CART_NOT_FOUND);
            }

        return 1;
    }

    @Override
    public int deleteChart(CartDelete cartDelete) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(cartDelete.getUser_id());
        cart.setProductId(cartDelete.getProduct_id());



            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",cart.getUserId())
                    .eq("product_id",cart.getProductId());

            int s=shoppingCartMapper.delete(queryWrapper);
            if (s!=1){
                throw new XmException(ExceptionEnum.GET_CART_NOT_FOUND);
            }

        return 1;

    }
}
