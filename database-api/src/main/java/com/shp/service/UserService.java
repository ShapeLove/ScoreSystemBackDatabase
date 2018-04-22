package com.shp.service;

import com.shp.model.User;
import com.shp.query.UserQuery;
import com.shp.result.BaseResult;
import com.shp.result.PageResult;

/**
 * Created by Shape on 2018/4/1.
 */
public interface UserService {

    /**
     * 根据用户的详细信息查询User
     * @return
     */
    User getUserByQuery(UserQuery query);

    /**
     * 根据Id查询单条记录
     */
    User queryUserById(Integer id);

    /**
     * 插入用户返回数据表id
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 批量查询用户
     * @param query
     * @return
     */
    PageResult<User> queryUserByPage(UserQuery query);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    Integer deleteUser(Integer id);

    /**
     * 更新用户
     */
    Integer updateUser(User user);
}
