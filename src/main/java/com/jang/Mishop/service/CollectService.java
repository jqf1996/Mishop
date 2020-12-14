package com.jang.Mishop.service;

import com.jang.Mishop.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

import com.jang.Mishop.vo.Collect.CoollectReq;
import com.jang.Mishop.vo.Collect.GetcollectByidReq;
import com.jang.Mishop.vo.Collect.GetcollextByidRes;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface CollectService extends IService<Collect> {
    void AddCollect(CoollectReq addCoollectReq);

    void DelCollect(CoollectReq addCoollectReq);

    List<GetcollextByidRes> getCollect(GetcollectByidReq getcollectByidReq);
}
