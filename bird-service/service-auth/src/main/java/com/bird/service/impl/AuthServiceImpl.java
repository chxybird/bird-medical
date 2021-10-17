package com.bird.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.dao.IAuthDao;
import com.bird.entity.Auth;
import com.bird.entity.Role;
import com.bird.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
@Service
@Slf4j
public class AuthServiceImpl extends ServiceImpl<IAuthDao, Auth> implements IAuthService, UserDetailsService {

    @Resource
    private IAuthDao authDao;

    /**
     * @Author lipu
     * @Date 2021/9/28 15:04
     * @Description 用户登录逻辑
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        //获取当前用户的详细信息
        Auth auth = authDao.getDetailsByAccount(account);
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

    /**
     * @Author lipu
     * @Date 2021/9/27 16:26
     * @Description 根据id查询用户详情 用户信息+角色信息+权限信息
     */
    @Override
    public Auth getDetailsById(Long id) {
        return authDao.getDetailsById(id);
    }

    /**
     * @Author lipu
     * @Date 2021/10/2 20:49
     * @Description 根据账号查询用户详情 用户信息+角色信息+权限信息
     */
    @Override
    public Auth getDetailsByAccount(String account) {
        return authDao.getDetailsByAccount(account);
    }
}
