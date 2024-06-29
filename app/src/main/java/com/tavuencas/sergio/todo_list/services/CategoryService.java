package com.tavuencas.sergio.todo_list.services;

import com.tavuencas.sergio.todo_list.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    List<CategoryDto> findAllByAppUserId(Long appUserId);

    void delete(Long id);

    List<CategoryDto> getAllTodoByCategoriesForToday(Long appUserId);
}
