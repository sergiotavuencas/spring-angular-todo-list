package com.tavuencas.sergio.todo_list.services;

import com.tavuencas.sergio.todo_list.dto.AppUserDto;

import java.util.List;

public interface AppUserService {

    AppUserDto save(AppUserDto appUserDto);

    List<AppUserDto> findAll();

    AppUserDto findById(Long id);

    void delete(Long id);

    AppUserDto login(AppUserDto appUserDto);
}
