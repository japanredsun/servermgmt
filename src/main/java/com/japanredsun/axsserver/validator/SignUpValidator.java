package com.japanredsun.axsserver.validator;

import com.japanredsun.axsserver.dao.UserInfoDAO;
import com.japanredsun.axsserver.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator{

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Autowired
    UserInfoDAO userInfoDAO;

    public boolean supports(Class<?> aClass) {
        return UserInfo.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

        UserInfo userInfo = (UserInfo) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userName","NotEmpty.userForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty.userForm.password");

        if(!emailValidator.valid(userInfo.getUserName())){
            errors.rejectValue("userName", "Pattern.userForm.email");
        }
    }
}
