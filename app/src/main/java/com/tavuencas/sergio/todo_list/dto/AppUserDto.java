package com.tavuencas.sergio.todo_list.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tavuencas.sergio.todo_list.models.AppUser;
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
public class AppUserDto {

    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime lastModifiedAt;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    @JsonIgnore
    private List<CategoryDto> categories;

    public static AppUser toEntity(AppUserDto appUserDto) {
        final AppUser appUser = new AppUser();

        appUser.setId(appUserDto.getId());
        appUser.setCreatedAt(appUserDto.getCreatedAt());
        appUser.setLastModifiedAt(appUserDto.getLastModifiedAt());
        appUser.setFirstName(appUserDto.getFirstName());
        appUser.setLastName(appUserDto.getLastName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setPassword(appUserDto.getPassword());
        appUser.setCategories(
                appUserDto.getCategories() != null ?
                        appUserDto.getCategories()
                                .stream()
                                .map(CategoryDto::toEntity)
                                .collect(Collectors.toList())
                        : null
        );

        return appUser;
    }

    public static AppUserDto fromEntity(AppUser appUser) {
        return AppUserDto.builder()
                .id(appUser.getId())
                .createdAt(appUser.getCreatedAt())
                .lastModifiedAt(appUser.getLastModifiedAt())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .userName(appUser.getUserName())
                .password(appUser.getPassword())
                .categories(
                        appUser.getCategories() != null ?
                                appUser.getCategories()
                                        .stream()
                                        .map(CategoryDto::fromEntity)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }
}
