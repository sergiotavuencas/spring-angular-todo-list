package com.tavuencas.sergio.todo_list.validators;

import com.tavuencas.sergio.todo_list.dto.TodoDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TodoValidator {

    public static List<String> validateTodo(TodoDto todoDto) {
        List<String> errors = new ArrayList<>();

        if (todoDto == null) {
            return List.of(
                    "Please fill the Title",
                    "Please fill the Description",
                    "Please fill the Category"
            );
        }

        if (!StringUtils.hasText(todoDto.getTitle())) {
            errors.add("Please fill the Title");
        }
        if (!StringUtils.hasText(todoDto.getDescription())) {
            errors.add("Please fill the Description");
        }
        if (todoDto.getCategory() == null) {
            errors.add("Please fill the Category");
        }

        return errors;
    }
}
