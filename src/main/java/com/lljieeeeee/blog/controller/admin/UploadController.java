package com.lljieeeeee.blog.controller.admin;

import com.lljieeeeee.blog.utils.result.R;
import com.lljieeeeee.blog.utils.upload.MultipartFileToFile;
import com.lljieeeeee.blog.utils.upload.kieng.KiengUtil;
import com.lljieeeeee.blog.utils.upload.kieng.UploadType;
import com.lljieeeeee.blog.utils.upload.yujian.YuJianUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lljieeeeee
 * @date 2021/6/2 13:49
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/upload")
public class UploadController {

    /**
     * 上传图片
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @ApiOperation("上传图片")
    @PostMapping("image")
    public R uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "   " + cookie.getValue());
            }
        }
        File file = MultipartFileToFile.multipartFileToFile(multipartFile);
        String url = YuJianUtil.upload(file);
        file.delete();
        return R.success().data("url", url);
    }
}
