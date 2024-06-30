package com.tavuencas.sergio.todo_list.services.impl;

import com.tavuencas.sergio.todo_list.dto.CategoryDto;
import com.tavuencas.sergio.todo_list.dto.TodoDto;
import com.tavuencas.sergio.todo_list.exception.EntityNotFoundException;
import com.tavuencas.sergio.todo_list.exception.ErrorCodes;
import com.tavuencas.sergio.todo_list.exception.InvalidEntityException;
import com.tavuencas.sergio.todo_list.models.Category;
import com.tavuencas.sergio.todo_list.models.Todo;
import com.tavuencas.sergio.todo_list.repositories.CategoryRepository;
import com.tavuencas.sergio.todo_list.repositories.TodoRepository;
import com.tavuencas.sergio.todo_list.services.TodoService;
import com.tavuencas.sergio.todo_list.validators.TodoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public TodoDto save(TodoDto todoDto) {
        List<String> errors = TodoValidator.validateTodo(todoDto);

        if (!errors.isEmpty()) {
            log.error("Todo is not valid {}", todoDto);
            throw new InvalidEntityException("Todo is not valid", ErrorCodes.TODO_NOT_VALID, errors);
        }

        return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long id) {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }

        Category category = new Category();
        category.setId(categoryRepository.findCategoryByTodoId(id));

        final Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(value -> value.setCategory(category));

        final TodoDto todoDto = TodoDto.fromEntity(todo.get());
        CategoryDto categoryDto = CategoryDto.fromEntity(category);
        todoDto.setCategory(categoryDto);

        return Optional.of(todoDto)
                .orElseThrow(() -> new EntityNotFoundException("No todo found with ID: " + id, ErrorCodes.TODO_NOT_FOUND));
    }

    @Override
    public List<TodoDto> findByCategoryId(Long categoryId) {
        return todoRepository.findTodoByCategoryId(categoryId)
                .stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Todo ID is null");
            return;
        }

        todoRepository.deleteById(id);
    }
}
