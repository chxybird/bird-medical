package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.Role;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
public interface IRoleDao extends BaseMapper<Role> {


    /**
     * 根据用户id查找角色信息
     *
     * @param authId 用户id
     * @return 角色集合
     */
    List<Role> findByAuthId(Long authId);

}
