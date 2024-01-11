package edu.ynu.se.xiecheng.achitectureclass.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.Response;
import edu.ynu.se.xiecheng.achitectureclass.dto.UserDto;
import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyCustomSuccessHandler implements AuthenticationSuccessHandler {


    @Resource
    private  JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        response.setContentType("application/json;charset=UTF-8");

        String token = jwtTokenUtil.generateToken(authentication.getName());
        user.setToken(token);
        System.out.println();
        UserDto userDto = new UserDto(user);
        new ObjectMapper().writeValue(response.getWriter(),userDto);
    }
}
