package com.talkingdata.controller;

import com.talkingdata.service.UserService;
import com.talkingdata.service.interf.UserServiceInf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * User：    ysl
 * Date:   2016/10/25
 * Time:   16:55
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger("test");

    private UserServiceInf userServiceInf;


    @Resource
    public void setUserServiceInf(UserServiceInf userServiceInf) {
        this.userServiceInf = userServiceInf;
    }

    @RequestMapping(value = "/login.do")
    public String userLogin(@RequestParam(value = "file", required = false)
                         MultipartFile file, HttpServletRequest request, ModelMap model)
    {
        boolean flag = userServiceInf.isExists();
        if(flag)
        {
            logger.info("用户登陆！");
        }
        return "index";
    }

    @RequestMapping(value = "/loginout.do")
    public String userLoginOut()
    {
        boolean flag = userServiceInf.isExists();
        if(flag)
        {
            logger.info("用户退出！");
        }
        return "index";
    }
}
