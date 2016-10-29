package com.talkingdata.service;

import com.talkingdata.dao.UserDao;
import com.talkingdata.dao.interf.UserDaoInterf;
import com.talkingdata.service.interf.UserServiceInf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User：    ysl
 * Date:   2016/10/25
 * Time:   16:55
 */

@Service
public class UserService implements UserServiceInf{


    private UserDaoInterf userDaoInterf;


    @Resource
    public void setUserDaoInterf(UserDaoInterf userDaoInterf) {
        this.userDaoInterf = userDaoInterf;
    }




    public boolean isExists()
    {
        return userDaoInterf.isExists();
    }

}
