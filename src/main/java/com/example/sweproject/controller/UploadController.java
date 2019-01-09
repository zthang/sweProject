package com.example.sweproject.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.sweproject.bean.ResultEntity;
import com.example.sweproject.service.UserService;
import com.example.sweproject.utils.AliyunOSSUtil;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.MARSHAL;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController
{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    private final String HEAD="https://sweproject.oss-cn-shanghai.aliyuncs.com/";

    @Autowired
    private UserService userService;
    /**
     * 文件上传
     * @param file
     */
    @RequestMapping(value = "/setUserPic",method = RequestMethod.POST)
    public ResultEntity setUserPic(HttpServletRequest request, @RequestParam("pic") MultipartFile file)
    {
        ResultEntity commonMessage=new ResultEntity();
        logger.info("============>文件上传");
        try {

            if(null != file){
                String filename = file.getOriginalFilename();
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    //上传到OSS
                    String uploadUrl = HEAD+AliyunOSSUtil.upload(newFile);
                    commonMessage.setState(userService.setUserPic(Integer.parseInt(request.getParameter("userID")),uploadUrl));
                    if(commonMessage.getState()==1)
                        commonMessage.setMessage("上传成功!");
                    else
                        commonMessage.setMessage("上传失败!");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return commonMessage;
    }
    @RequestMapping(value = "/getUserPic",method = RequestMethod.POST)
    public String getUserPic(@Param("userID")Integer userID)
    {
        Map map=new HashMap();
        map.put("url",userService.getUserPic(userID));
        return JSONUtils.toJSONString(map);
    }

}
