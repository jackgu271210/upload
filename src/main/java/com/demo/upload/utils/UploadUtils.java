package com.demo.upload.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    //定义一个目标路径,就是我们要把图片上传到的位置
    private static final String BASE_PATH="D:\\Java\\apache-tomcat-9.0.65\\webapps\\upload\\";
    
    //定义访问图片的路径
    private static final String SERVER_PATH="http://localhost:8080/upload/";
    
    public static String upload(MultipartFile file) {
        //获得上传文件的名称
        String filename = file.getOriginalFilename();
        //为了保证图片在服务中名字的唯一性，这个是我们要用UUID来对filename进行改写
        String uuid = UUID.randomUUID().toString().replace("-","");
        //将生成的uuid和filename进行拼接
        String newFilename = uuid + "-" +filename;
        //创建一个文件实例对象
        File image = new File(BASE_PATH, newFilename);
        //对这个文件进行上传的操作
        try {
            file.transferTo(image);
        } catch (IOException e) {
            return null;
        }
        
        return SERVER_PATH + newFilename;
    }
}
