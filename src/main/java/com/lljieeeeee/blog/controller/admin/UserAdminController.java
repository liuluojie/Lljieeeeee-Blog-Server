package com.lljieeeeee.blog.controller.admin;


import cn.dev33.satoken.stp.StpUtil;
import com.lljieeeeee.blog.entity.User;
import com.lljieeeeee.blog.service.UserService;
import com.lljieeeeee.blog.util.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lljieeeeee
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @ApiOperation("根据Token获取用户信息")
    @GetMapping("getUserInfoByToken")
    public R getUserInfoByToken(HttpServletRequest request) {
        boolean isLogin = StpUtil.isLogin();
        if (!isLogin) {
            return R.error().message("没有权限，请登录");
        }
        User user = userService.getById((String)StpUtil.getLoginId());
        return R.success().data("user", user);
    }
}

