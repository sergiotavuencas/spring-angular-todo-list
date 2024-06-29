package com.tavuencas.sergio.todo_list.repositories;

import com.tavuencas.sergio.todo_list.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodoByCategoryId(Long categoryId);
}
