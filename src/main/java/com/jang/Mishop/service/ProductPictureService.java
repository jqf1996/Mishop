package com.jang.Mishop.service;

import com.jang.Mishop.entity.ProductPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jang.Mishop.vo.ProductPic;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface ProductPictureService extends IService<ProductPicture> {
        List<ProductPicture> GetProductPic(ProductPic productPic);
}
