package com.example.sweproject.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantProperties implements InitializingBean {

    @Value("${image.endpoint}")
    private String image_endpoint;

    @Value("${image.keyid}")
    private String image_keyid;

    @Value("${image.keysecret}")
    private String image_keysecret;

    @Value("${image.filehost}")
    private String image_filehost;

    @Value("${image.bucketname1}")
    private String image_bucketname1;


    public static String IMAGE_END_POINT         ;
    public static String IMAGE_ACCESS_KEY_ID     ;
    public static String IMAGE_ACCESS_KEY_SECRET ;
    public static String IMAGE_BUCKET_NAME1      ;
    public static String IMAGE_FILE_HOST         ;

    @Override
    public void afterPropertiesSet() throws Exception {
        IMAGE_END_POINT = image_endpoint;
        IMAGE_ACCESS_KEY_ID = image_keyid;
        IMAGE_ACCESS_KEY_SECRET = image_keysecret;
        IMAGE_FILE_HOST = image_filehost;
        IMAGE_BUCKET_NAME1 = image_bucketname1;
    }
}