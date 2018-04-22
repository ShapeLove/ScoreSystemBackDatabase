package com.shp.service;

import com.shp.model.Permission;
import com.shp.model.Role;
import com.shp.query.RoleQuery;
import com.shp.result.BaseResult;
import com.shp.result.PageResult;

import java.util.List;

/**
 * Created by Shape on 2018/4/21.
 */
public interface RoleService {

    /**
     * 分页查询权限
     * @param query
     * @return
     */
    PageResult<Role> queryRolePage(RoleQuery query);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    Role queryOneRole(Integer id);

    /**
     * 插入角色
     * @param role
     * @return
     */
    Integer insertRole(Role role);

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    Integer deleteRole(Integer id);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    Integer updateRole(Role role);

    /**
     * 根据Role的id查询该角色的所有权限
     * @param id
     * @return
     */
    List<Permission> queryRolePermissions(Integer id);
}
