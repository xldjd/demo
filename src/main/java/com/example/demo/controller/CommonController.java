package com.example.demo.controller;


import com.example.demo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basepath;

    @RequestMapping("/upload")
    public R<String> upload(MultipartFile file) {
log.info("开始文件上传{}",file.toString());
//原始文件名
String orignfilename=file.getOriginalFilename();

String suffix=orignfilename.substring(orignfilename.lastIndexOf("."));
//使用uuid重命名文件，防止文件名重复
String filename= UUID.randomUUID().toString()+suffix;

//创建一个目录对象
        File dir = new File(basepath);
        //判断当前目录是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }

try {
    file.transferTo(new File(basepath+filename));
}catch (IOException e){
    e.printStackTrace();
};
return R.success(filename);
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response, String name){
        try {
            FileInputStream fileInputStream=new FileInputStream(new File(basepath+name));

            ServletOutputStream servletOutputStream=response.getOutputStream();

            response.setContentType("image/jpeg");

            int len=0;
            byte[] bytes=new byte[1024];

         while ((len= fileInputStream.read(bytes))!=-1){
             servletOutputStream.write(bytes,0,len);
             servletOutputStream.flush();
         }
         servletOutputStream.close();
         fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
