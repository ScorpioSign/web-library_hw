package ru.skypro.homework.springboot.weblibrary_hw.security;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @JoinColumn(name = "user_id")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> roleList;

}
