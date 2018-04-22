package com.shp.serviceImpl.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shp.Contant.ResultEnum;
import com.shp.mapper.PermissionAndRoleMapper;
import com.shp.mapper.RoleCustomMapper;
import com.shp.mapper.RoleMapper;
import com.shp.mapper.UserMapper;
import com.shp.model.*;
import com.shp.query.RoleQuery;
import com.shp.result.PageResult;
import com.shp.service.RoleService;
import com.shp.util.DateUtil;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.management.relation.RoleUnresolved;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Shape on 2018/4/21.
 */
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionAndRoleMapper permissionAndRoleMapper;

    @Autowired
    private RoleCustomMapper roleCustomMapper;

    @Override
    public PageResult<Role> queryRolePage(RoleQuery query) {
        if (null == query) {
            throw new RuntimeException(ResultEnum.QUERY_NULL.getMessage());
        }
        PageResult<Role> pageResult = new PageResult<>();
        pageResult.setPageSize(query.getPageSize());
        pageResult.setPageIndex(query.getPageIndex());
        UserExample example = null;
        UserExample.Criteria criteria = null;
        User user = null;
        if (null != query.getUserName()) {
            example = new UserExample();
            criteria = example.createCriteria().andUserNameEqualTo(query.getUserName());
        }
        if (null != example) {
            List<User> users = userMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(users)) {
                return pageResult;
            }else {
                Role role = roleMapper.selectByPrimaryKey(users.get(0).getRoleId());
                if (null != role) {
                    pageResult.setContent(Arrays.asList(role));
                }
                return pageResult;
            }
        }
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<Role> roles = roleMapper.selectByExample(new RoleExample());
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        long total = pageInfo.getTotal(); //获取总记录数
        pageInfo.setTotal(total);
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

    @Override
    public Role queryOneRole(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer insertRole(Role role) {
        if (null == role) {
            throw new RuntimeException(ResultEnum.INSERT_PARAM_NULL.getMessage());
        }
        role.setUpdateTime(DateUtil.localDate2Date(LocalDate.now()));
        return roleMapper.insertSelective(role);
    }

    @Override
    @Transactional
    public Integer deleteRole(Integer id) {
        int n = roleMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            PermissionAndRoleExample example = new PermissionAndRoleExample();
            PermissionAndRoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(id);
            int n2 = permissionAndRoleMapper.deleteByExample(example);
        }
        return n;
    }

    @Override
    public Integer updateRole(Role role) {
        if (null == role) {
            throw new RuntimeException(ResultEnum.UPDATE_PARAM_NULL.getMessage());
        }
        role.setUpdateTime(DateUtil.localDate2Date(LocalDate.now()));
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<Permission> queryRolePermissions(Integer id) {
        return roleCustomMapper.selectPermissionByRoleId(id);
    }
}
