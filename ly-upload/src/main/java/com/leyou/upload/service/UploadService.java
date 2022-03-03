package com.leyou.upload.service;


import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    //数组按保存需要的数据类型
    private   static   final List<String> suffixes=Arrays.asList("image/png","image/jpg");
    public String  upload(MultipartFile file) throws Exception {



        //图片类型的验证
        String type = file.getContentType();
        if(!suffixes.contains(type)){
            throw   new Exception("图片类型不符合");

        }

        //内容的验证

        BufferedImage read = ImageIO.read(file.getInputStream());
        if (read==null){
            throw   new Exception("图片类型不符合");
        }

        //保存文件

        File  dir=new File("D:\\");
        try {
            file.transferTo(new File(dir,file.getOriginalFilename()));//拿到地址和文件

        } catch (IOException e) {
            throw   new Exception(e);
        }

        //返回路径
        String  url="http://192.168.8.206"+file.getOriginalFilename();


        String path="D:\\"+file.getOriginalFilename();

      //  downloadPicture(url,path);


        return url;
    }


    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
