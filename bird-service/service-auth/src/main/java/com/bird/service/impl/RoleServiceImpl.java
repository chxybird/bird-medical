package com.bird.service.impl;

import com.bird.entity.Role;
import com.bird.dao.IRoleDao;
import com.bird.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李璞
 * @since 2021-09-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<IRoleDao, Role> implements IRoleService {

}
