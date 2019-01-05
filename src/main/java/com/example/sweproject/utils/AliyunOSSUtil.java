package com.example.sweproject.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.sweproject.constant.ConstantProperties;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AliyunOSSUtil
{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);


    public static String upload(File file)
    {
        logger.info("=========>OSS文件上传开始："+file.getName());
        String endpoint= ConstantProperties.IMAGE_END_POINT;
        String accessKeyId=ConstantProperties.IMAGE_ACCESS_KEY_ID;
        String accessKeySecret=ConstantProperties.IMAGE_ACCESS_KEY_SECRET;
        String bucketName=ConstantProperties.IMAGE_BUCKET_NAME1;
        String fileHost=ConstantProperties.IMAGE_FILE_HOST;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if(null == file)
        {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try
        {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost+"/"+(dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if(null != result)
            {
                logger.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                return fileUrl;
            }
        }
        catch (OSSException oe)
        {
            logger.error(oe.getMessage());
        }
        catch (ClientException ce)
        {
            logger.error(ce.getMessage());
        }
        finally
        {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

}
