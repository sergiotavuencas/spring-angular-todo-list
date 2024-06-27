package com.tavuencas.sergio.todo_list.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class AppUser extends BaseEntity {

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(length = 30)
    private String userName;

    @Column(length = 20)
    private String password;

    @OneToMany(mappedBy = "appUser")
    private List<Category> categories;
}
