package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.Permission;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
public interface IPermissionDao extends BaseMapper<Permission> {


    /**
     * 根据角色id查找权限信息
     *
     * @param roleId 角色id
     * @return 权限集合
     */
    List<Permission> findByRoleId(Long roleId);

}
