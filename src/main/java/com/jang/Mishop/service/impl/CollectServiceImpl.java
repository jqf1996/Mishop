package com.jang.Mishop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jang.Mishop.entity.Collect;
import com.jang.Mishop.entity.Product;
import com.jang.Mishop.entity.ShoppingCart;
import com.jang.Mishop.mapper.CollectMapper;
import com.jang.Mishop.mapper.ProductMapper;
import com.jang.Mishop.service.CollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jang.Mishop.vo.Collect.CoollectReq;
import com.jang.Mishop.vo.Collect.GetcollectByidReq;
import com.jang.Mishop.vo.Collect.GetcollextByidRes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public void AddCollect(CoollectReq addCoollectReq) {
        Collect collect = new Collect();
        collect.setUserId(addCoollectReq.getUser_id());
        collect.setProductId(addCoollectReq.getProduct_id());
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(collect);
        Collect selectOne=collectMapper.selectOne(queryWrapper);//先查询是否存在收藏

        if(selectOne!=null){
            collect.setCollectTime(new Date().getTime());
            collectMapper.insert(collect);
        }
        else {
            DelCollect(addCoollectReq); //调用删除
        }

    }

    @Override
    public void DelCollect(CoollectReq addCoollectReq) {
        Collect collect = new Collect();
        collect.setUserId(addCoollectReq.getUser_id());
        collect.setProductId(addCoollectReq.getProduct_id());
        collect.setCollectTime(new Date().getTime());

        //执行取消
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(collect);
        collectMapper.delete(queryWrapper);
    }

    @Override
    public List<GetcollextByidRes> getCollect(GetcollectByidReq getcollectByidReq) {
        Collect collect = new Collect();
        collect.setUserId(getcollectByidReq.getUser_id());
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.setEntity(collect);

        List<Collect> list = collectMapper.selectList(wrapper);


        List<GetcollextByidRes> result = new ArrayList();

        for (Collect c : list) {
            Product product = productMapper.selectById(c.getProductId());
            GetcollextByidRes res = new GetcollextByidRes();
            BeanUtils.copyProperties(product,res);
            result.add(res);
        }




        return result;
    }
}
