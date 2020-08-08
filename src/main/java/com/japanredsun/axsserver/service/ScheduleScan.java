package com.japanredsun.axsserver.service;

import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleScan {

    @Autowired
    private ServerInfoDAO serverInfoDAO;

    //private EmailServiceImp emailServiceImp = new EmailServiceImp();

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("Spring-Mail.xml");

    private EmailServiceImp emailServiceImp = (EmailServiceImp) context.getBean("mailMail");
    private List<Server> servers;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Date today = Calendar.getInstance().getTime();
    private final Calendar cal = Calendar.getInstance();
    private final String from = "nhmnhat@tma.com.vn";

    //Schedule at 8 a.m daily
    @Scheduled(cron="0 0 8 * * *")
    public void dailyScan() throws ParseException {
        servers = serverInfoDAO.getRegisteredServer();
        cal.add(Calendar.DATE,+1);
        String reportday = df.format(today);
        String nextday = df.format(cal.getTime());

        if(!servers.isEmpty()){
            for (Server server : servers) {
                String enddate = server.getEndDate();

                if(enddate.equals(reportday)){
                    //send email
                    emailServiceImp.sendSimpleEmail(from,server.getOwner(),"Unregister expiry server","Your server " +
                            server.getServer() + " has been unregisted");
                    serverInfoDAO.unregister(server);
                    System.out.println("Unregister server");
                }else if(enddate.equals(nextday)){
                    //send reminding email
                    emailServiceImp.sendSimpleEmail(from,server.getOwner(),"Notice","Your server "+server.getServer()
                            +" will expire soon. Extend if you want to use more");
                    System.out.println("Sent email");
                }
            }
        }else {
//            emailServiceImp.sendSimpleEmail("nhmnhat@tma.com.vn","nhmnhat@tma.com.vn","Notice","Your server"
//                    +" will expire soon. Extend if you want to use more");
            System.out.println("No registered server");
        }



    }
}
