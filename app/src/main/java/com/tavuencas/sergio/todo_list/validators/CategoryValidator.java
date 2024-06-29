package com.tavuencas.sergio.todo_list.validators;

import com.tavuencas.sergio.todo_list.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validateCategory(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto == null) {
            return List.of(
                    "Please fill the Name",
                    "Please fill the Description"
            );
        }

        if (!StringUtils.hasText(categoryDto.getName())) {
            errors.add("Please fill the Name");
        }
        if (!StringUtils.hasText(categoryDto.getDescription())) {
            errors.add("Please fill the Description");
        }

        return errors;
    }
}
