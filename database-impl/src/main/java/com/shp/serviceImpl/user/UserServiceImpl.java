package com.shp.serviceImpl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shp.Contant.ResultEnum;
import com.shp.mapper.ScoreMapper;
import com.shp.mapper.UserMapper;
import com.shp.model.ScoreExample;
import com.shp.model.User;
import com.shp.model.UserExample;
import com.shp.query.UserQuery;
import com.shp.result.BaseResult;
import com.shp.result.PageResult;
import com.shp.service.ScoreService;
import com.shp.service.UserService;
import com.shp.util.ResultFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by Shape on 2018/4/2.
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public User getUserByQuery(UserQuery query) {
        if (Objects.isNull(query)) {
            throw new RuntimeException("查询对象为空");
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(query.getUserName())) {
            criteria.andUserNameEqualTo(query.getUserName());
        }
        if (Objects.nonNull(query.getUserBirthday())) {
            criteria.andUserBirthdayEqualTo(query.getUserBirthday());
        }
        if (Objects.nonNull(query.getUserEmail())) {
            criteria.andUserEmailEqualTo(query.getUserEmail());
        }
        if (Objects.nonNull(query.getUserPhone())) {
            criteria.andUserPhoneEqualTo(query.getUserPhone());
        }
        if (Objects.nonNull(query.getUserPassword())) {
            criteria.andUserPasswordEqualTo(query.getUserPassword());
        }
        List<User> userList = userMapper.selectByExample(example);

        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

    @Override
    public User queryUserById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("ID为空");
        }
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public Integer insertUser(User user) {
        if (Objects.isNull(user)) {
            throw new RuntimeException("插入的用户对象为空");
        }
        if (StringUtils.isEmpty(user.getUserPassword()) || StringUtils.isEmpty(user.getUserName())) {
            throw new RuntimeException("插入的用户用户名和密码不能为空");
        }
        int n = userMapper.insertSelective(user);
        return n;
    }

    @Override
    public PageResult<User> queryUserByPage(UserQuery query) {
        if (Objects.isNull(query)) {
            throw new RuntimeException(ResultEnum.QUERY_NULL.getMessage());
        }
        PageResult<User> result = new PageResult<>();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(query.getUserName())) {
            criteria.andUserNameEqualTo(query.getUserName());
        }
        if (Objects.nonNull(query.getUserBirthday())) {
            criteria.andUserBirthdayEqualTo(query.getUserBirthday());
        }
        if (Objects.nonNull(query.getUserEmail())) {
            criteria.andUserEmailEqualTo(query.getUserEmail());
        }
        if (Objects.nonNull(query.getUserPhone())) {
            criteria.andUserPhoneEqualTo(query.getUserPhone());
        }
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<User> users = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        long total = pageInfo.getTotal(); //获取总记录数
        result.setPageIndex(query.getPageIndex());
        result.setPageSize(query.getPageSize());
        result.setTotal(total);
        result.setContent(pageInfo.getList());
        return result;
    }

    @Override
    @Transactional
    public Integer deleteUser(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("删除时Id不能为空");
        }
        int n = userMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            ScoreExample example = new ScoreExample();
            ScoreExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(id);
            int n2 = scoreMapper.deleteByExample(example);
        }
        return n;
    }

    @Override
    public Integer updateUser(User user) {
        if (Objects.isNull(user)) {
            throw new RuntimeException("更新对象不能为空");
        }
        int n = userMapper.updateByPrimaryKeySelective(user);
        return n;
    }
}
