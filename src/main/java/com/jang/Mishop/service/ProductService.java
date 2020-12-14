package com.jang.Mishop.service;

import com.github.pagehelper.PageInfo;
import com.jang.Mishop.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jang.Mishop.vo.Product.GetProByCatgIdReq;
import com.jang.Mishop.vo.Product.GetProByIdReq;
import com.jang.Mishop.vo.Product.GetProductByPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface ProductService extends IService<Product> {
    List<Product> getProductByCatgId(GetProByCatgIdReq getProByCatgIdReq);

    List<Product> getHotProduct();

    Product getProductByid(GetProByIdReq getProByIdReq);

    PageInfo<Product> getProductByPage(GetProductByPage byPage);
}
