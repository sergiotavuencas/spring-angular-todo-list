package com.tavuencas.sergio.todo_list.services.impl;

import com.tavuencas.sergio.todo_list.dto.AppUserDto;
import com.tavuencas.sergio.todo_list.exception.EntityNotFoundException;
import com.tavuencas.sergio.todo_list.exception.ErrorCodes;
import com.tavuencas.sergio.todo_list.exception.InvalidEntityException;
import com.tavuencas.sergio.todo_list.repositories.AppUserRepository;
import com.tavuencas.sergio.todo_list.services.AppUserService;
import com.tavuencas.sergio.todo_list.validators.AppUserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUserDto save(AppUserDto appUserDto) {
        List<String> errors = AppUserValidator.validateAppUser(appUserDto);

        if (!errors.isEmpty()) {
            log.error("User is not valid {}", appUserDto);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }

        return AppUserDto.fromEntity(appUserRepository.save(AppUserDto.toEntity(appUserDto)));
    }

    @Override
    public List<AppUserDto> findAll() {
        return appUserRepository.findAll()
                .stream()
                .map(AppUserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AppUserDto findById(Long id) {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }

        return appUserRepository.findById(id)
                .map(AppUserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user found with ID: " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("User ID is null");
            throw new EntityNotFoundException("No user found with ID: " + id, ErrorCodes.USER_NOT_FOUND);
        }

        appUserRepository.deleteById(id);
    }

    @Override
    public AppUserDto login(AppUserDto appUserDto) {
        List<String> errors = AppUserValidator.validateAppUserCredentials(appUserDto.getEmail(), appUserDto.getPassword());

        if (!errors.isEmpty()) {
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }

        return appUserRepository.findAppUserByEmailAndPassword(appUserDto.getEmail(), appUserDto.getPassword())
                .map(AppUserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user fond with Email: " + appUserDto.getEmail() + ", and Password: <HIDDEN_PASSWORD>", ErrorCodes.USER_NOT_FOUND));
    }
}
