package com.lljieeeeee.blog.util.upload;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Lljieeeeee
 * @date 2021/7/20 21:32
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
public class MultipartFileToFile {

    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (StringUtils.isEmpty(file)) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 获取流文件
     * @param ins
     * @param file
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
