package com.talkingdata.dao;


import com.talkingdata.dao.interf.UserDaoInterf;
import org.springframework.stereotype.Repository;

/**
 * User：    ysl
 * Date:   2016/10/25
 * Time:   16:55
 */
@Repository
public class UserDao implements UserDaoInterf{

    public boolean isExists()
    {
        return true;
    }
}
