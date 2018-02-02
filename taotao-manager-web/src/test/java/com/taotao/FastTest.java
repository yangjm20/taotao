package com.taotao;


import com.taotao.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

public class FastTest {

    @Test
    public void testUpload() throws Exception {
        //1.把FastDFS提供的jar包添加到工程中
        //2.初始化全局配置。加载一个配置文件
        ClientGlobal.init("E:\\itheima\\taotao\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        //3.创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //4.创建一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.声明一个StorageServer对象，null
        StorageServer storageServer = null;
        //6.获得StorageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //7.直接调用StorageClient对象方法上传文件即可
        String[] jpgs = storageClient.upload_file("C:\\Users\\yangjm\\Desktop\\timg.jpg", "jpg", null);
        for (String str : jpgs) {
            System.out.println(str);
        }
    }

    @Test
    public void testFastDFS() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("E:\\itheima\\taotao\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        String jpg = fastDFSClient.uploadFile("C:\\Users\\yangjm\\Desktop\\u=2585817365,4270330179&fm=200&gp=0.jpg", "jpg");
        System.out.println(jpg);
    }
}
