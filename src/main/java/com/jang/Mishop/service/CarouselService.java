package com.jang.Mishop.service;

import com.jang.Mishop.entity.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
public interface CarouselService extends IService<Carousel> {
    List<Carousel> getAllCarousel();
}
