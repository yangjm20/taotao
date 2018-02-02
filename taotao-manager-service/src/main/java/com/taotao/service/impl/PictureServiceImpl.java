package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {
    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Override
    public PictureResult uploadPic(MultipartFile picFile){
        PictureResult pictureResult=new PictureResult();
        String picName=picFile.getOriginalFilename();
        String extName=picName.substring(picName.lastIndexOf(".")+1);
        if(picFile.isEmpty()){
            pictureResult.setError(1);
            pictureResult.setMessage("图片为空");
            return  pictureResult;
        }
        FastDFSClient fastDFSClient= null;
        try {
            fastDFSClient = new FastDFSClient("classpath:properties/client.conf");
            String url = fastDFSClient.uploadFile(picFile.getBytes(), extName);
            url=IMAGE_SERVER_BASE_URL+url;
            pictureResult.setError(0);
            pictureResult.setMessage("图片上传成功");
            pictureResult.setUrl(url);
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败");
        }



        return pictureResult;
    }
}
