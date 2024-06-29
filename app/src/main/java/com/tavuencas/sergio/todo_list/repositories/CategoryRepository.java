package com.tavuencas.sergio.todo_list.repositories;

import com.tavuencas.sergio.todo_list.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByAppUserId(Long appUserId);

    @Query("SELECT t.category.id FROM Todo t WHERE t.id = :todoId")
    Long findCategoryByTodoId(Long todoId);

    @Query(
            "SELECT c FROM Category c " +
                    "INNER JOIN Todo t on t.category.id = c.id " +
                    "WHERE t.startDate >= :startDate " +
                    "AND t.startDate <= :endDate " +
                    "AND c.app_user.id = :appUserId")
    List<Category> getAllTodoByCategoriesForToday(
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate,
            @Param("appUserId") Long appUserId
    );
}
