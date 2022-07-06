package com.lljieeeeee.blog.controller.admin;

import com.lljieeeeee.blog.util.result.R;
import com.lljieeeeee.blog.util.upload.MultipartFileToFile;
import com.lljieeeeee.blog.util.upload.yujian.YuJianUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
        // TODO: 校验用户是否登录
        File file = null;
        String url = null;
        try {
            file = MultipartFileToFile.multipartFileToFile(multipartFile);
            url = YuJianUtil.upload(file);
        } finally {
            // 不管图片是否上传成功都应该删除服务器上的图片
            file.delete();
        }
        return R.success().data("url", url);
    }
}
