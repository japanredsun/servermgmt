package com.japanredsun.axsserver.controller;

import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private ServerInfoDAO serverInfoDAO;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String addServer(@ModelAttribute("serverForm") Server server, BindingResult result, Model model, final RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "adminPage";
        }
        try {
            serverInfoDAO.saveOrUpdate(server);
        }catch (Exception e)
        {
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "adminPage";
        }
        System.out.println("Add server " + server.getServer() + " successfully!");

        return  "redirect:/admin/add?success=true";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addSuccess(Model model, @RequestParam(required = false) boolean success){
        Server server = new Server();
        model.addAttribute("serverForm",server);
        if(success){
            model.addAttribute("message","Add successfully!");
        }
        return "adminPage";
    }
}
