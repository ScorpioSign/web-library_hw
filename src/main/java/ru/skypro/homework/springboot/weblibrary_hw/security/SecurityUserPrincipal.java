package ru.skypro.homework.springboot.weblibrary_hw.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SecurityUserPrincipal implements UserDetails {
    private AuthUser user;


    private List<SecurityGrand> securityGrandList;

    public SecurityUserPrincipal(AuthUser user) {
        this.user = user;
        this.securityGrandList = user.getRoleList().stream()
                .map(SecurityGrand::new)
                .toList();
    }

    // Возвращает авторитеты (роли) пользователя.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(securityGrandList);
    }

    @Override
    // Возвращает пароль пользователя.
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    // Возвращает имя пользователя.
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    // Возвращает true, если аккаунт пользователя не истек.
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // Возвращает true, если аккаунт пользователя не заблокирован.
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // Возвращает true, если учётные данные пользователя не истекли.
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // Возвращает true, если пользователь включён (активен).
    public boolean isEnabled() {
        return true;
    }

}
