package com.shp.mapper;

import com.shp.model.PermissionAndRole;
import com.shp.model.PermissionAndRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionAndRoleMapper {
    int countByExample(PermissionAndRoleExample example);

    int deleteByExample(PermissionAndRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionAndRole record);

    int insertSelective(PermissionAndRole record);

    List<PermissionAndRole> selectByExample(PermissionAndRoleExample example);

    PermissionAndRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionAndRole record, @Param("example") PermissionAndRoleExample example);

    int updateByExample(@Param("record") PermissionAndRole record, @Param("example") PermissionAndRoleExample example);

    int updateByPrimaryKeySelective(PermissionAndRole record);

    int updateByPrimaryKey(PermissionAndRole record);
}