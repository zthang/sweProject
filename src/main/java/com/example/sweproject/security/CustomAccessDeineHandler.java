package com.example.sweproject.security;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//已认证用户无权访问
public class CustomAccessDeineHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException e)throws IOException
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("message", "没有访问权限!");
        map.put("state",104);
        response.getWriter().print(JSONUtils.toJSONString(map));
    }
}
