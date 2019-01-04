package com.example.sweproject.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.sweproject.bean.LoginMessage;
import com.example.sweproject.bean.User;
import com.example.sweproject.utils.JwtTokenUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/loginByPhone");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User loginUser=new User();
        loginUser.setUsername(request.getParameter("phoneNumber"));
        loginUser.setPassword(request.getParameter("password"));
        rememberMe.set(1);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)throws IOException
    {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        boolean isRemember = rememberMe.get() == 1;
        System.out.println("jwtUser:" + jwtUser.toString());
        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        // 根据用户名，角色创建token
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("message", "登陆成功");
        map.put("state",1);
        map.put("userID",jwtUser.getId());
        map.put("nickname",jwtUser.getNickname());
        response.getWriter().write(JSONUtils.toJSONString(map));
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("message", "手机号或密码错误！");
        map.put("state",0);
        response.getWriter().write(JSONUtils.toJSONString(map));
    }
}
