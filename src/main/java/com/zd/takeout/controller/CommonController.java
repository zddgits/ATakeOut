package com.zd.takeout.controller;

import com.zd.takeout.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.logging.Filter;

@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {
    @Value("${zd.path}")
    private String basePath;
    /**
     * 文件上传
     * @param file 文件名
     * @return 执行结果
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        //file为一临时文件，如果不及时转存，后面会直接被清理
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info(file.toString());
        try {
            file.transferTo(new File(basePath + newFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(newFileName);
    }

    /**
     * 文件下载
     * @param name 文件名
     * @param response 响应
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            log.info(name);
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            response.setContentType("image/jpeg");
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
