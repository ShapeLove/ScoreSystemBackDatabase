package com.shp.serviceImpl.permission;

import com.shp.Contant.ResultEnum;
import com.shp.mapper.PermissionAndRoleMapper;
import com.shp.mapper.PermissionMapper;
import com.shp.model.Permission;
import com.shp.model.PermissionAndRoleExample;
import com.shp.model.PermissionExample;
import com.shp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Shape on 2018/4/21.
 */
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionAndRoleMapper permissionAndRoleMapper;

    @Override
    public List<Permission> queryPermission(Permission query) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        if (query != null) {
            if(null != query.getMenuName()) {
                criteria.andMenuNameEqualTo(query.getMenuName());
            }
        }
        List<Permission> permissions = permissionMapper.selectByExample(example);
        return permissions;
    }

    @Override
    public Integer updatePermission(Permission permission) {
        if (null == permission) {
            throw new RuntimeException(ResultEnum.UPDATE_PARAM_NULL.getMessage());
        }
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public Integer insertPermission(Permission permission) {
        if (null == permission) {
            throw new RuntimeException(ResultEnum.INSERT_PARAM_NULL.getMessage());
        }
        return permissionMapper.insertSelective(permission);
    }

    @Override
    @Transactional
    public Integer deletePermission(Integer id) {
        int n = permissionMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            PermissionAndRoleExample example = new PermissionAndRoleExample();
            PermissionAndRoleExample.Criteria criteria = example.createCriteria();
            criteria.andPermissionIdEqualTo(id);
            int n2 = permissionAndRoleMapper.deleteByExample(example);
        }
        return n;
    }
}
