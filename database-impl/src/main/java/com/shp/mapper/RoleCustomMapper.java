package com.shp.mapper;

import com.shp.model.Permission;

import java.util.List;

/**
 * Created by Shape on 2018/4/21.
 */
public interface RoleCustomMapper {

    List<Permission> selectPermissionByRoleId(Integer roleId);
}
