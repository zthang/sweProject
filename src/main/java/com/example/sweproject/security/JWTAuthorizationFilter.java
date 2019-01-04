package com.example.sweproject.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.sweproject.bean.CommonMessage;
import com.example.sweproject.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        try {
            // 如果请求头中没有Authorization信息则直接放行了
            if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }
            // 如果请求头中有token，则进行解析，并且设置认证信息
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            super.doFilterInternal(request, response, chain);
        }
        catch (ExpiredJwtException e)
        {
            logger.error("token已过期");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> map = new HashMap<>();
            map.put("message", "token已过期！");
            map.put("state",101);
            response.getWriter().write(JSONUtils.toJSONString(map));
        }
        catch (Exception e)
        {
            logger.error("token内容错误");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> map = new HashMap<>();
            map.put("message", "token内容错误！");
            map.put("state",102);
            response.getWriter().write(JSONUtils.toJSONString(map));
        }
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        String role = JwtTokenUtils.getUserRole(token);
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
        }
        return null;
    }
}
