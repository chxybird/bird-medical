package com.bird.service.impl;

import com.bird.entity.Permission;
import com.bird.dao.IPermissionDao;
import com.bird.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<IPermissionDao, Permission> implements IPermissionService {

}
