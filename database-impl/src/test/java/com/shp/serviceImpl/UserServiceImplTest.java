package com.shp.serviceImpl;

import com.shp.Application;
import com.shp.model.User;
import com.shp.query.UserQuery;
import com.shp.result.BaseResult;
import com.shp.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Shape on 2018/4/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

}