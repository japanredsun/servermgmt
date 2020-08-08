package com.japanredsun.axsserver.controller;

import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.dao.UserInfoDAO;
import com.japanredsun.axsserver.entity.Server;
import com.japanredsun.axsserver.model.UserInfo;
import com.japanredsun.axsserver.validator.SignUpValidator;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
@Transactional

public class MainController {
    @Autowired
    private ServerInfoDAO serverInfoDAO;

    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model){
        model.addAttribute("title","Welcome");
        model.addAttribute("servers",serverInfoDAO.getAllServers());
        return "welcomePage";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        Server server = new Server();
        model.addAttribute("serverForm",server);
        model.addAttribute("title","Admin Page");
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        model.addAttribute("message","Logout Successful!");
        return "notice";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        Server server = new Server();
        String owner = principal.getName();
        model.addAttribute("serverForm",server);
        model.addAttribute("title","Register Server");
        model.addAttribute("servers",serverInfoDAO.getServersByOwner(owner));

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        }
        return "403page";
    }
}
