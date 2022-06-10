package com.lljieeeeee.blog.service;

import com.lljieeeeee.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lljieeeeee.blog.vo.admin.UserAdminVO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param userAdminVO
     * @return
     */
    String login(UserAdminVO userAdminVO);
}
