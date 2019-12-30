package com.mhacioglu.warehouse.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mhacioglu.warehouse.domain.User;
import com.mhacioglu.warehouse.service.UserService;

@Component
public class SignupValidator implements Validator {
    private final UserService userService;

    @Autowired
    public SignupValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User form = (User) o;
        validateUsername(errors, form);
    }

    private void validateUsername(Errors errors, User form) {
        if (userService.getUserByUsername(form.getUsername()) != null) {
            errors.reject("username.exists", "Bu kullanıcı adı zaten kullanılıyor");
        }
    }
}
