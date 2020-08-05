package com.fh.sqh.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public class OssUtils {

    public static String uploadFile(File file) throws Exception {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4GBP2X1tWTo6u578ut4r";
        String accessKeySecret = "BWi8yIzQTFu6I5zc9ompnR4jum6C7X";

        String buckName = "1908a-sunqihang";

        String name = file.getName();
        String substringName = name.substring(name.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+substringName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        InputStream inputStream = new FileInputStream(file.getPath());
        PutObjectRequest putObjectRequest = new PutObjectRequest(buckName,newFileName,inputStream);

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传字符串。
        ossClient.putObject(putObjectRequest);

        String sub = endpoint.substring(endpoint.indexOf("o"));
        String sss = "https://"+buckName+"."+sub+"/"+newFileName;
        // 关闭OSSClient。
        ossClient.shutdown();
        return sss;
    }

}
