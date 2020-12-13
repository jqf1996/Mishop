package com.jang.Mishop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jang.Mishop.entity.User;
import com.jang.Mishop.exception.ExceptionEnum;
import com.jang.Mishop.exception.XmException;
import com.jang.Mishop.mapper.UserMapper;
import com.jang.Mishop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jang.Mishop.util.MD5Util;
import com.jang.Mishop.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jqf
 * @since 2020-12-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResultMessage resultMessage;
    @Override
    public void register(User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.inSql("username","select username from user");
//        queryWrapper.notLike("username",user.getUsername());
        queryWrapper.eq("username",user.getUsername());//字段在这个表的栏目存在

        //查询用户是否存在
        if(userMapper.selectOne(queryWrapper)!=null){
            resultMessage.fail("202","用户名重复");
            throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
            //抛出异常 枚举类
        }


        else
        {
            if(user.getUsername().length()<5 ){
                resultMessage.fail("202","用户名长度不够");
                throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
            }
            if(user.getPassword().length()<5){
                resultMessage.fail("202","密码长度不够");
                throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
            }

            user1.setPassword(MD5Util.MD5Encode(user.getPassword() + "", "UTF-8"));
            //MD5加密

            try {
                userMapper.insert(user1); //执行插入表
            } catch (Exception e) {
                throw new XmException(ExceptionEnum.SAVE_USER_ERROR);//抛出异常 枚举类
            }
        }
    }

    @Override
    public User login(User user) {
        user.setPassword(MD5Util.MD5Encode(user.getPassword() + "", "UTF-8"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username",user.getUsername())
                .eq("password",user.getPassword());
        User user1=userMapper.selectOne(queryWrapper);
        if (user1 == null) {
            throw new XmException(ExceptionEnum.GET_USER_NOT_FOUND);
        }
        return user1;
    }


}
