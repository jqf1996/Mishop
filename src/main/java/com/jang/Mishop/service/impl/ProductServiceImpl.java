package com.jang.Mishop.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jang.Mishop.entity.Product;
import com.jang.Mishop.entity.ProductPicture;
import com.jang.Mishop.exception.ExceptionEnum;
import com.jang.Mishop.exception.XmException;
import com.jang.Mishop.mapper.ProductMapper;
import com.jang.Mishop.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jang.Mishop.vo.GetProByCatgIdReq;
import com.jang.Mishop.vo.GetProByIdReq;
import com.jang.Mishop.vo.GetProductByPage;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> getProductByCatgId(GetProByCatgIdReq getProByCatgIdReq) {
        Product product = new Product();
        product.setCategoryId(getProByCatgIdReq.getCatgoryId());

        PageHelper.startPage(0, 2);
        List<Product> list=null;//定义一个集合list 引用类型时productpic

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("product_sales")//按照产品销量排序
                .setEntity(product);

        try {
            list=productMapper.selectList(queryWrapper);//查询集合
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
        }
        return list;
    }

    @Override
    public List<Product> getHotProduct() {
        List<Product> list=null;//定义一个集合list 引用类型时productpic
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("product_sales");

        try {
            list=productMapper.selectList(queryWrapper);//查询集合
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
        }
        return list;
    }

    @Override
    public Product getProductByid(GetProByIdReq getProByIdReq) {
        Product product = new Product();
        product.setProductId(getProByIdReq.getId());
//        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("product_sales");
        Product product1 = productMapper.selectById(product.getProductId());
        return product1;

    }

    @Override
    public PageInfo<Product> getProductByPage(GetProductByPage byPage) {

        List<Product> list=null;
        PageHelper.startPage((byPage.getCurentId())- 1, byPage.getPageSize(), true);
        if(byPage.getCatgoryId()==0){
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            list=productMapper.selectList(queryWrapper);//
        }
        else {
            GetProByCatgIdReq getProByCatgIdReq = new GetProByCatgIdReq();
            getProByCatgIdReq.setCatgoryId(byPage.getCatgoryId());
            list=getProductByCatgId(getProByCatgIdReq);//调用上面的分类函数
        }
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        return pageInfo;

    }
}
