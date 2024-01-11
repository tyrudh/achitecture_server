package edu.ynu.se.xiecheng.achitectureclass.service;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.UserRole;
import edu.ynu.se.xiecheng.achitectureclass.security.CustomUserDetails;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class MyUserDetailsService implements UserDetailsService {
    /*
     *  UserDetailsService：提供查询用户功能，如根据用户名查询用户，并返回UserDetails
     *UserDetails，SpringSecurity定义的类， 记录用户信息，如用户名、密码、权限等
     * */
    @Resource
    private CustomerDao customerDao;
    @Resource
    private BusinessDao businessDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            //根据用户名从数据库中查询用户
        System.out.println("自定义登录服务-loadUserName方法执行");

        //首先判断是不是Business
        Business business = businessDao.findByUsername(username);

        //如果不是Business
        if (business == null){
            Customer customer = customerDao.findByUsername(username);

            if (customer == null){
                System.out.println("用户名不存在"+username+"不存在");
                throw new UsernameNotFoundException("用户不存在");
            }else {
                List<String> authorities = new ArrayList<>();
                authorities.add("ROLE_customer");
                CustomUserDetails customUserDetails = new CustomUserDetails(
                        customer,
                        AuthorityUtils.createAuthorityList("ROLE_customer") // 或者您可以添加实际的权限
                );
                return customUserDetails;
            }

        }else {
            List<String> authorities = new ArrayList<>();
            authorities.add("ROLE_business");
            CustomUserDetails customUserDetails = new CustomUserDetails(
                    business,
                    AuthorityUtils.createAuthorityList("ROLE_business") // 或者您可以添加实际的权限
            );
            return customUserDetails;
        }

    }
}

