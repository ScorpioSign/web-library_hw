package ru.skypro.homework.springboot.weblibrary_hw.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityGrand implements GrantedAuthority {
    private String role;

    public SecurityGrand(Role role) {
        {
            this.role = role.getRole();
        }
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

}

