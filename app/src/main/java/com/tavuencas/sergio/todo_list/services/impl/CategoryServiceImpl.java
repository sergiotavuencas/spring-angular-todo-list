package com.tavuencas.sergio.todo_list.services.impl;

import com.tavuencas.sergio.todo_list.dto.CategoryDto;
import com.tavuencas.sergio.todo_list.exception.EntityNotFoundException;
import com.tavuencas.sergio.todo_list.exception.ErrorCodes;
import com.tavuencas.sergio.todo_list.exception.InvalidEntityException;
import com.tavuencas.sergio.todo_list.repositories.CategoryRepository;
import com.tavuencas.sergio.todo_list.services.CategoryService;
import com.tavuencas.sergio.todo_list.validators.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validateCategory(categoryDto);

        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", categoryDto);
            throw new InvalidEntityException("Category is not valid", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }

        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null) {
            log.error("Category ID is null");
            return null;
        }

        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No category found with ID: " + id, ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAllByAppUserId(Long appUserId) {
        return categoryRepository.findCategoryByAppUserId(appUserId)
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Category ID is null");
            return;
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllTodoByCategoriesForToday(Long appUserId) {
        return categoryRepository.getAllTodoByCategoriesForToday(
                        ZonedDateTime.now().withHour(0).withMinute(0),
                        ZonedDateTime.now().withHour(23).withMinute(59), appUserId)
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
