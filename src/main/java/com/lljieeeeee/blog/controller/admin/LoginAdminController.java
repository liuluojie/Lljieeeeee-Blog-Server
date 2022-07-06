package com.lljieeeeee.blog.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import com.lljieeeeee.blog.service.UserService;
import com.lljieeeeee.blog.util.result.R;
import com.lljieeeeee.blog.vo.admin.UserAdminVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lljieeeeee
 * @date 2021/11/23 21:54
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@RestController
@RequestMapping("/admin/login")
public class LoginAdminController {

    @Autowired
    private UserService userService;


    @ApiOperation("用户登录")
    @PostMapping("login")
    public R login(@RequestBody UserAdminVO userAdminVO) {
        String token = userService.login(userAdminVO);
        if (StringUtils.isEmpty(token)) {
            return R.error().message("登录失败！");
        }
        return R.success().message("登录成功!").data("Lljieeeeee-Token", token);
    }

    @ApiOperation(value = "查询当前登录状态")
    @GetMapping("isLogin")
    public R isLogin() {
        return R.success().data("isLogin", StpUtil.isLogin());
    }
}
