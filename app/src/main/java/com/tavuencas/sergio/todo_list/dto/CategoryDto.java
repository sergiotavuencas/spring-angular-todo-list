package com.tavuencas.sergio.todo_list.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tavuencas.sergio.todo_list.models.AppUser;
import com.tavuencas.sergio.todo_list.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime lastModifiedAt;

    private String name;

    private String description;

    @JsonIgnore
    private AppUser appUser;

    private List<TodoDto> todoList;

    public static Category toEntity(CategoryDto categoryDto) {
        final Category category = new Category();

        category.setId(categoryDto.getId());
        category.setCreatedAt(categoryDto.getCreatedAt());
        category.setLastModifiedAt(categoryDto.getLastModifiedAt());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setAppUser(categoryDto.getAppUser());
        category.setTodoList(
                categoryDto.getTodoList() != null ?
                        categoryDto.getTodoList()
                                .stream()
                                .map(TodoDto::toEntity)
                                .collect(Collectors.toList())
                        : null
        );

        return category;
    }

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .createdAt(category.getCreatedAt())
                .lastModifiedAt(category.getLastModifiedAt())
                .name(category.getName())
                .description(category.getDescription())
                .appUser(category.getAppUser())
                .todoList(
                        category.getTodoList() != null ?
                                category.getTodoList().stream()
                                        .map(TodoDto::fromEntity)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }
}
