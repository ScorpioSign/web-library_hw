package ru.skypro.homework.springboot.weblibrary_hw.security;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// Создаем класс SecurityUserDetailsService, который реализует интерфейс UserDetailsService
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired

    private final UserRepository userRepository;

    @Override
        public UserDetails loadUserByUsername(String username) {
        AuthUser user = userRepository.findByUsername(username);
                if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new SecurityUserPrincipal(user);
    }
}