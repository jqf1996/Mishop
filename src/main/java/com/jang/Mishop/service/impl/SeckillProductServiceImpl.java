package com.jang.Mishop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jang.Mishop.entity.*;
import com.jang.Mishop.exception.ExceptionEnum;
import com.jang.Mishop.exception.XmException;
import com.jang.Mishop.mapper.ProductMapper;
import com.jang.Mishop.mapper.SeckillProductMapper;
import com.jang.Mishop.mapper.SeckillTimeMapper;
import com.jang.Mishop.service.ProductService;
import com.jang.Mishop.service.SeckillProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jang.Mishop.util.RedisKey;
import com.jang.Mishop.vo.Collect.GetcollextByidRes;
import com.jang.Mishop.vo.Product.GetProByCatgIdReq;
import com.jang.Mishop.vo.Skill_product.GetSkillProductReq;
import com.jang.Mishop.vo.Skill_product.GetskillProductRes;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@Service
public class SeckillProductServiceImpl extends ServiceImpl<SeckillProductMapper, SeckillProduct> implements SeckillProductService {

    @Autowired
    private ProductMapper productMapper ;
    @Autowired
    private SeckillProductMapper seckillProductMapper;

    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SeckillTimeMapper seckillTimeMapper;

    @Override
    public List<GetskillProductRes> GetProductByTimeId(GetSkillProductReq getSkillProductReq)  {

        //先从redis缓存中读

        List<GetskillProductRes> li = redisTemplate.opsForList().range(RedisKey.SECKILL_PRODUCT_LIST + getSkillProductReq.getTime_id(), 0, -1);
        if (ArrayUtils.isNotEmpty(li.toArray())) {
            return li;
        }


        List<GetskillProductRes> result = new ArrayList();//List返回类型是一个res实体
        //找到这个时间点所有的秒杀产品list
        QueryWrapper<SeckillProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("time_id",getSkillProductReq.getTime_id());
        List<SeckillProduct> list = seckillProductMapper.selectList(queryWrapper);

        //遍历list 得到产品信息 一个个加进去
        for(SeckillProduct c: list){
            GetskillProductRes res = skillProductRes(c.getSeckillId());//调用封装好的函数
            result.add(res);
        }

        if (ArrayUtils.isNotEmpty(result.toArray())) {
            //写入缓存
            redisTemplate.opsForList().leftPushAll(RedisKey.SECKILL_PRODUCT_LIST + getSkillProductReq.getTime_id(), result);
            // 设置过期时间
            System.out.println("写入成功");
//            long l = result.get(0).getEndTime() - new Date().getTime();
//            redisTemplate.expire(RedisKey.SECKILL_PRODUCT_LIST + getSkillProductReq.getTime_id(), l, TimeUnit.MILLISECONDS);
        } else {
            // 秒杀商品过期或不存在
            throw new XmException(ExceptionEnum.GET_SECKILL_NOT_FOUND);
        }

        return result;
    }



















    public GetskillProductRes skillProductRes(int seckill_id) {
        GetskillProductRes getskillProductRes = new GetskillProductRes();
        //拿到product的实体
        QueryWrapper<SeckillProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("seckill_id",seckill_id);
        SeckillProduct seckillProduct = seckillProductMapper.selectOne(queryWrapper);
        //selectByid 好像是主键

        //设置返回价格
        getskillProductRes.setProductPrice(seckillProduct.getSeckillPrice());


        QueryWrapper<SeckillTime> Wrapper = new QueryWrapper<>();
        SeckillTime seckillTime = seckillTimeMapper.selectById(seckillProduct.getTimeId());
        //设置秒杀的开始和结束时间
        getskillProductRes.setStartTime(seckillTime.getStartTime());
        getskillProductRes.setEndTime(seckillTime.getEndTime());

        //查找product表 设置产品名称和价格

        Product product1 = productMapper.selectById(seckillProduct.getProductId());

        getskillProductRes.setProductName(product1.getProductName());
        getskillProductRes.setProductPicture(product1.getProductPicture());

        return getskillProductRes;

//        List<SeckillProduct> list = seckillProductMapper.selectList(queryWrapper);
//
//        List<GetskillProductRes> result = new ArrayList();//List返回类型是一个res实体
//
//        for (SeckillProduct c : list) {  //遍历收藏的list
////            QueryWrapper<Product> wrap = new QueryWrapper<>();
////            wrap.eq("product_id",c.getProductId());
////            Product product = productMapper.selectOne(wrap);
//            Product product = productMapper.selectById(c.getProductId());
//            GetskillProductRes res = new GetskillProductRes();//new一个返回实体
//            BeanUtils.copyProperties(product, res);
//            result.add(res);//list add
//        }
//        return result;
    }
}