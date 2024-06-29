package com.tavuencas.sergio.todo_list.repositories;

import com.tavuencas.sergio.todo_list.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByEmailAndPassword(String email, String password);
}
