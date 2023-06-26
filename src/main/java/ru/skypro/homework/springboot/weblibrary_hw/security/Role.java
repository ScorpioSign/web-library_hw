package ru.skypro.homework.springboot.weblibrary_hw.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


    @Getter
    @Setter
    @Entity
    @Table(name = "roles")
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String role;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "auth_user_id")
        private AuthUser user;
    }

