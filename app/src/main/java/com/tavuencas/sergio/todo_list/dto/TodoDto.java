package com.tavuencas.sergio.todo_list.dto;

import com.tavuencas.sergio.todo_list.models.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime lastModifiedAt;

    private String title;

    private String description;

    private ZonedDateTime startDate;

    private boolean isDone;

    private boolean isFavorite;

    private CategoryDto category;

    public static Todo toEntity(TodoDto todoDto) {
        final Todo todo = new Todo();

        todo.setId(todoDto.getId());
        todo.setCreatedAt(todoDto.getCreatedAt());
        todo.setLastModifiedAt(todoDto.getLastModifiedAt());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setStartDate(todoDto.getStartDate());
        todo.setDone(todo.isDone());
        todo.setFavorite(todo.isFavorite());
        todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));

        return todo;
    }

    public static TodoDto fromEntity(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .createdAt(todo.getCreatedAt())
                .lastModifiedAt(todo.getLastModifiedAt())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .isDone(todo.isDone())
                .isFavorite(todo.isFavorite()).build();
    }
}
