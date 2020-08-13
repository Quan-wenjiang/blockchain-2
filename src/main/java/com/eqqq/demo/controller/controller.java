package com.eqqq.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class controller {

    @PostMapping(value = "/uploading")
    public @ResponseBody
    String uploadFile(@RequestParam("filename") MultipartFile file) {

        if (file.isEmpty()) {

            return "上传文件为空";
        }
        String fileName = file.getOriginalFilename();

        String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."),fileName.length());

        String templatePath = "src/main/resources/static/assets/images";
//        log.info("文件路径:" + templatePath);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取文件名
//        String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
        // 解决中文问题,liunx 下中文路径,图片显示问题
        //fileName = UUID.randomUUID() + suffixName;
        File dest0 = new File(templatePath);
        File dest = new File(dest0.getAbsoluteFile()+ File.separator + fileName);
        //文件上传-覆盖
        try {
            // 检测是否存在目录
            if (!dest0.getParentFile().exists()) {
                dest0.getParentFile().mkdirs();
                //检测文件是否存在
            }
//            if (!dest.exists()) {
//                dest.mkdirs();
//            }
            file.transferTo(dest);
            return "上传成功";
        } catch (Exception e) {
//            log.error("文件上传错误");
            return "上传失败";
        }

    }

}
