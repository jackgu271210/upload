package com.demo.upload.controller;

import com.demo.upload.entity.DataJson;
import com.demo.upload.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Controller
@RequestMapping("upload")
public class UploadController {
    
    @RequestMapping("image")
    @ResponseBody
    public DataJson uploadImage(@RequestParam(value="file") MultipartFile multipartFile) {
        //调用我们写的上传文件的工具类
        String imagePath = UploadUtils.upload(multipartFile);  
        DataJson dataJson = new DataJson();
        if (imagePath != null) {
            dataJson.setCode(1);
            dataJson.setMsg("上传成功");
            HashMap<String, String> map = new HashMap<>();
            map.put("src",imagePath);
            dataJson.setData(map);
        }else{
            dataJson.setCode(0);
            dataJson.setMsg("对不起，上传失败");
        }

        return dataJson;
    }
    
    @RequestMapping("addImage")
    @ResponseBody
    public String addImage(String goodsImage, String imageDescribe) {
        //是要把图片的访问路径添加到数据库中
        System.out.println("当前图片的访问路径:" + goodsImage);
        System.out.println("当前图片的描述信息:" + imageDescribe);
        
        return "1";
    }
}
