package com.japanredsun.axsserver.controller;

import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.dao.UserInfoDAO;
import com.japanredsun.axsserver.entity.Server;
import com.japanredsun.axsserver.model.UserInfo;
import com.japanredsun.axsserver.validator.InputValidator;
import com.japanredsun.axsserver.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Transactional
@RequestMapping("server")
public class ServerController {
    @Autowired
    InputValidator inputValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(inputValidator);
    }

    @Autowired
    private ServerInfoDAO serverInfoDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String serverlist(Model model){
        Server server = new Server();
        model.addAttribute("serverForm",server);
        model.addAttribute("title","Register Server");
        model.addAttribute("servers",serverInfoDAO.getAvailableServers());
        return "registerPage";
    }

    @RequestMapping(value = "/{id}/update",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String serverInfo(@ModelAttribute(value = "serverForm") Server server, @PathVariable(value = "id") long id, BindingResult result, final RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors())
        {
            return "registerPage";
        }
        try {
            serverInfoDAO.updateServer(server);
        }catch (Exception e)
        {
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "registerPage";
        }
        return "redirect:/server/"+server.getId()+"/update?success=true";
    }

    @RequestMapping(value = "/{id}/update",method = RequestMethod.GET)
    public String updateSuccess(Model model, @PathVariable(value = "id") long id, @RequestParam(required = false) boolean success){
        Server server = serverInfoDAO.getServerById(id);
        model.addAttribute("servers",serverInfoDAO.getAvailableServers());
        model.addAttribute("serverForm",server);
        if(success){
            model.addAttribute("message","Update " + server.getServer() + " successfully!");
        }
        return "registerPage";
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String deleteSV(@ModelAttribute(value = "serverForm") Server server, @PathVariable(value = "id") long id, BindingResult result, final RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors())
        {
            return "registerPage";
        }
        try {
            serverInfoDAO.deleteServer(server.getId());
        }catch (Exception e)
        {
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "registerPage";
        }
        return "redirect:/server/"+server.getId()+"/delete?success=true";
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    public String deleteSuccess(Model model, @PathVariable(value = "id") long id, @RequestParam(required = false) boolean success){
        Server server = new Server();
        model.addAttribute("serverForm",server);
        model.addAttribute("servers",serverInfoDAO.getAvailableServers());
        if(success){
            model.addAttribute("message","Delete successfully!");
        }
        return "registerPage";
    }

    @RequestMapping(value = "/{id}/register",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String register(@ModelAttribute(value = "serverForm") @Validated  Server server, @PathVariable(value = "id") long id, BindingResult result, final RedirectAttributes redirectAttributes, Model model, Principal principal){
        if(result.hasErrors())
        {
            return "registerPage";
        }
        try {
            String currentUser = principal.getName();
            serverInfoDAO.register(server,currentUser);
        }catch (Exception e)
        {
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "registerPage";
        }
        return "redirect:/server/"+server.getId()+"/register?success=true";
    }

    @RequestMapping(value = "/{id}/register",method = RequestMethod.GET)
    public String registersuccess(Model model,Principal principal, @PathVariable(value = "id") long id, @RequestParam(required = false) boolean success){
        Server server = serverInfoDAO.getServerById(id);
        String username = principal.getName();
        model.addAttribute("servers",serverInfoDAO.getServersByOwner(username));
        model.addAttribute("serverForm",server);
        if(success){
            model.addAttribute("message","Register " + server.getServer() + " successfully!");
        }
        return "userInfoPage";
    }

    @RequestMapping(value = "/{id}/unregister",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String unregister(@ModelAttribute(value = "serverForm") Server server, @PathVariable(value = "id") long id, BindingResult result, final RedirectAttributes redirectAttributes, Model model, Principal principal){
        if(result.hasErrors())
        {
            return "userInfoPage";
        }
        try {
            serverInfoDAO.unregister(server);
        }catch (Exception e)
        {
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "userInfoPage";
        }
        model.addAttribute("title","Register Server");
        model.addAttribute("servers",serverInfoDAO.getServersByOwner(principal.getName()));
        model.addAttribute("message","Unregister " + server.getServer() + " successful!");
        return "userInfoPage";
    }

    @RequestMapping(value = "/{id}/extend",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String extend(@ModelAttribute(value = "serverForm") @Validated Server server, @PathVariable(value = "id") long id, BindingResult result, final RedirectAttributes redirectAttributes, Model model, Principal principal){
        if(result.hasErrors())
        {
            return "userInfoPage";
        }
        try {
            serverInfoDAO.extendEndDate(server);
        }catch (Exception e)
        {
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "userInfoPage";
        }
        model.addAttribute("title","Register Server");
        model.addAttribute("servers",serverInfoDAO.getServersByOwner(principal.getName()));
        model.addAttribute("message","Extend " + server.getServer() + " successful!");
        return "userInfoPage";
    }

}
