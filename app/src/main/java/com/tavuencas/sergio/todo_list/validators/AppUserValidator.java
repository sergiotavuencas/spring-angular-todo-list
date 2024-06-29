package com.tavuencas.sergio.todo_list.validators;

import com.tavuencas.sergio.todo_list.dto.AppUserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppUserValidator {

    public static List<String> validateAppUser(AppUserDto appUserDto) {
        List<String> errors = new ArrayList<>();

        if (appUserDto == null) {
            return List.of(
                    "Please fill the First Name",
                    "Please fill the Last Name",
                    "Please fill the Username",
                    "Please fill the Email",
                    "Please fill the Password"
            );
        }

        Map<String, String> fieldErrors = Map.of(
                appUserDto.getFirstName(), "Please fill the First name",
                appUserDto.getLastName(), "Please fill the Last name",
                appUserDto.getUserName(), "Please fill the User name",
                appUserDto.getEmail(), "Please fill the user Email",
                appUserDto.getPassword(), "Please fill the User Password"
        );

        fieldErrors.forEach((field, errorMessage) -> {
            if (!StringUtils.hasText(field)) {
                errors.add(errorMessage);
            }
        });

        return errors;
    }

    public static List<String> validateAppUserCredentials(String email, String password) {
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(email)) {
            errors.add("Email is empty");
        }
        if (!StringUtils.hasText(password)) {
            errors.add("Password is empty");
        }

        return errors;
    }
}
