package com.japanredsun.axsserver.controller;

import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.dao.UserInfoDAO;
import com.japanredsun.axsserver.entity.Server;
import com.japanredsun.axsserver.model.PaginationResult;
import com.japanredsun.axsserver.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Transactional
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private ServerInfoDAO serverInfoDAO;

    @RequestMapping(value = {"/test"},method = RequestMethod.GET)
    public String signup(Model model){
        return "test";
    }


}
