package com.tavuencas.sergio.todo_list.services;

import com.tavuencas.sergio.todo_list.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto save(TodoDto todoDto);

    List<TodoDto> findAll();

    TodoDto findById(Long id);

    List<TodoDto> findByCategoryId(Long categoryId);

    void delete(Long id);
}
