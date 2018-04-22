package com.shp.service;

import com.shp.model.Permission;
import com.shp.result.BaseResult;

import java.util.List;

/**
 * Created by Shape on 2018/4/21.
 */
public interface PermissionService {

    /**
     * 根据特定值查询权限信息
     * @param query
     * @return
     */
    List<Permission> queryPermission(Permission query);

    /**
     * 根据id修改权限信息
     * @param permission
     * @return
     */
    Integer updatePermission(Permission permission);

    /**
     * 插入权限信息
     * @param permission
     * @return
     */
    Integer insertPermission(Permission permission);

    /**
     * 根据id删除权限信息
     * @param id
     * @return
     */
    Integer deletePermission(Integer id);
}
