package com.japanredsun.axsserver.validator;

import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class InputValidator implements Validator {
    @Autowired
    ServerInfoDAO serverInfoDAO;


    public boolean supports(Class<?> aClass) {
        return Server.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

        Server server = (Server) o;
        String start = server.getStartDate();
        String end = server.getEndDate();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"startDate","NotEmpty.serverForm.startDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"endDate","NotEmpty.serverForm.endDate");

        try {
            if(!start.equals("") && !end.equals(""))
            {
                Date enddate = formatter.parse(start);
                Date startdate = formatter.parse(end);
                if(startdate.before(enddate)){
                    errors.rejectValue("endDate","Valid.serverForm.endDate");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
}
