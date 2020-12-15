package com.jang.Mishop.service.impl;

import com.jang.Mishop.entity.Carousel;
import com.jang.Mishop.mapper.CarouselMapper;
import com.jang.Mishop.service.CarouselService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
    @Autowired
    private CarouselService carouselService;

    @Override
    public List<Carousel> getAllCarousel() {
        List<Carousel> allCarousel = carouselService.getAllCarousel();
        return allCarousel;

    }
}
