package com.lljieeeeee.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lljieeeeee.blog.dto.admin.UserAdminDTO;
import com.lljieeeeee.blog.entity.User;
import com.lljieeeeee.blog.mapper.UserMapper;
import com.lljieeeeee.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljieeeeee.blog.utils.md5.Md5;
import com.lljieeeeee.blog.vo.admin.UserAdminVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(UserAdminVO userAdminVO) {
        if (StringUtils.isEmpty(userAdminVO)) {
            return null;
        }
        String email = userAdminVO.getEmail();
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        String password = userAdminVO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        User selectOne = baseMapper.selectOne(wrapper);
        if (StringUtils.isEmpty(selectOne)) {
            return null;
        }
        String passwordMd5 = selectOne.getPassword();

        boolean verify = Md5.verify(password, passwordMd5);
        if (verify) {
            StpUtil.login(selectOne.getUserId());
            return StpUtil.getTokenInfo().getTokenValue();
        }
        return null;
    }
}
