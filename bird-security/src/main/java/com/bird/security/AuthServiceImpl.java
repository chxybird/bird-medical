package com.bird.security;

import com.bird.entity.Auth;
import com.bird.entity.Role;
import com.bird.feign.IAuthFeign;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lipu
 * @Date 2021/10/4 17:14
 * @Description
 */
@Component
public class AuthServiceImpl implements UserDetailsService {

    @Resource
    private IAuthFeign authFeign;


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        //获取当前用户的详细信息
        Auth auth = authFeign.getDetailsByAccount(account);
        if (auth == null) {
            return null;
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roleList = auth.getRoleList();
        if (roleList != null) {
            authorityList = roleList.stream().map(item -> {
                return (GrantedAuthority) new SimpleGrantedAuthority(item.getRoleCode());
            }).collect(Collectors.toList());
        }
        //构建Security的认证用户信息 此时该对象会被SpringSecurity其他过滤器链处理自动比较密码
        return new User(auth.getAccount(), auth.getPassword(), authorityList);
    }
}
