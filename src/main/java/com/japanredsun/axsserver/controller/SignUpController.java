package com.japanredsun.axsserver.controller;

import com.japanredsun.axsserver.dao.UserInfoDAO;
import com.japanredsun.axsserver.model.UserInfo;
import com.japanredsun.axsserver.validator.SignUpValidator;
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

@Controller
@Transactional
public class SignUpController {
    @Autowired
    SignUpValidator signUpValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(signUpValidator);
    }

    @Autowired
    private UserInfoDAO userInfoDAO;

    @RequestMapping(value = {"/signup"},method = RequestMethod.GET)
    public String signup(Model model){
        UserInfo userInfo = new UserInfo();
        model.addAttribute("userForm",userInfo);
        return "signup";
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String userSave(@ModelAttribute("userForm")  @Validated UserInfo userInfo, BindingResult result, Model model, final RedirectAttributes redirectAttributes){

        if(result.hasErrors())
        {
            return "signup";
        }
        try {
            userInfoDAO.save(userInfo);
            userInfoDAO.initRole(userInfo);
        }catch (Exception e){
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "signup";
        }

        model.addAttribute("title", "Sign up");
        model.addAttribute("message","Sign up successfully!");
        return "notice";
    }
}
