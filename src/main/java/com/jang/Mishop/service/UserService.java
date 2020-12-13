package com.jang.Mishop.service;

import com.jang.Mishop.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jang.Mishop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@Service
public interface UserService extends IService<User> {

    void register(User user);

    User login(User user);


}
