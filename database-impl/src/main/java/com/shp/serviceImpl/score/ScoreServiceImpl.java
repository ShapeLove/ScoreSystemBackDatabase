package com.shp.serviceImpl.score;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shp.Contant.ResultEnum;
import com.shp.mapper.ScoreMapper;
import com.shp.mapper.UserMapper;
import com.shp.model.Score;
import com.shp.model.ScoreExample;
import com.shp.model.User;
import com.shp.model.UserExample;
import com.shp.query.ScoreQuery;
import com.shp.result.BaseResult;
import com.shp.result.PageResult;
import com.shp.service.ScoreService;
import com.shp.util.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: sunhaipeng
 * Date: 2018/4/4
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer insertScore(Score score) {
        if (null == score || null == score.getUserId()) {
            throw new RuntimeException("插入对象不能为空并且userId不能为空");
        }
        int n = scoreMapper.insertSelective(score);
        return n;
    }

    @Override
    public PageResult<Score> queryScoreByPage(ScoreQuery query) {
        PageResult<Score> result = new PageResult();
        result.setPageSize(query.getPageSize());
        result.setPageIndex(query.getPageIndex());
        User user = null;
        if (Objects.nonNull(query.getUserName()) || Objects.nonNull(query.getUserPhone())) {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            if (Objects.nonNull(query.getUserName())) {
                criteria.andUserNameEqualTo(query.getUserName());
            }
            if (Objects.nonNull(query.getUserPhone())) {
                criteria.andUserPhoneEqualTo(query.getUserPhone());
            }
            List<User> users = userMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(users)) {
                user = users.get(0);
            }
        }
        ScoreExample example2 = new ScoreExample();
        ScoreExample.Criteria criteria2 = example2.createCriteria();
        if (Objects.nonNull(user)) {
            criteria2.andUserIdEqualTo(user.getId());
        }
        PageHelper.startPage(result.getPageIndex(),result.getPageSize());
        List<Score> scoreList = scoreMapper.selectByExample(example2);
        PageInfo<Score> scorePageInfo = new PageInfo<>(scoreList);
        result.setTotal(scorePageInfo.getTotal());
        result.setContent(scorePageInfo.getList());
        return result;
    }

    @Override
    public Score queryScoreById(Integer id) {
        if (null != id) {
            throw new RuntimeException("查询成绩时Id不能为空");
        }
        Score score = scoreMapper.selectByPrimaryKey(id);
        return score;
    }

    @Override
    public Integer updateScore(Score score) {
        if (null == score || null == score.getId()) {
            throw new RuntimeException("更改成绩时对象不能为空并且id不能为空");
        }
        int n = scoreMapper.updateByPrimaryKeySelective(score);
        return n;
    }

    @Override
    public Integer deleteScore(Integer id) {
        int n = scoreMapper.deleteByPrimaryKey(id);
        return n;
    }
}
