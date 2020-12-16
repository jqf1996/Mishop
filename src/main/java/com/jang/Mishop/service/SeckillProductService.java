package com.jang.Mishop.service;

import com.jang.Mishop.entity.SeckillProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jang.Mishop.vo.Product.GetProByCatgIdReq;
import com.jang.Mishop.vo.Skill_product.GetSkillProductReq;
import com.jang.Mishop.vo.Skill_product.GetskillProductRes;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface SeckillProductService extends IService<SeckillProduct> {
    List<GetskillProductRes> GetProductByTimeId(GetSkillProductReq getSkillProductReq);


}
