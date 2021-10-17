package com.bird.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bird.entity.Auth;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
public interface IAuthService extends IService<Auth> {

    /**
     * 根据id查询用户详情 用户信息+角色信息+权限信息
     *
     * @param id 用户id
     * @return 用户详细信息
     */
    Auth getDetailsById(Long id);

    /**
     * 根据账号查询用户详情 用户信息+角色信息+权限信息
     *
     * @param account 用户账号
     * @return 用户详细信息
     */
    Auth getDetailsByAccount(String account);

}
