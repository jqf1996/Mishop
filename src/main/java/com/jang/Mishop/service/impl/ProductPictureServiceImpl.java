package com.jang.Mishop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jang.Mishop.entity.ProductPicture;
import com.jang.Mishop.entity.ShoppingCart;
import com.jang.Mishop.exception.ExceptionEnum;
import com.jang.Mishop.exception.XmException;
import com.jang.Mishop.mapper.ProductPictureMapper;
import com.jang.Mishop.service.ProductPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jang.Mishop.vo.ProductPic;
import org.apache.commons.lang3.ArrayUtils;
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
public class ProductPictureServiceImpl extends ServiceImpl<ProductPictureMapper, ProductPicture> implements ProductPictureService {

    @Autowired
   private ProductPictureMapper pictureMapper;


    @Override
    public List<ProductPicture> GetProductPic(ProductPic productPic) {
        ProductPicture picture = new ProductPicture();
        picture.setProductId(productPic.getProduct_id());
        List<ProductPicture> list=null;//定义一个集合list 引用类型时productpic

        QueryWrapper<ProductPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(picture);
        try {
            list=pictureMapper.selectList(queryWrapper);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_PICTURE_NOT_FOUND);
            }
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_PICTURE_NOT_FOUND);
        }
        return list;


    }
}
