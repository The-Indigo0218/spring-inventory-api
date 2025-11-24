package indigodev.com.co.springinventoryapi.config;

import indigodev.com.co.springinventoryapi.domain.User;
import indigodev.com.co.springinventoryapi.domain.enums.UserRole;
import indigodev.com.co.springinventoryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User superAdmin = User.builder()
                    .username("root")
                    .password(passwordEncoder.encode("root"))
                    .role(UserRole.SUPER_ADMIN)
                    .build();
            userRepository.save(superAdmin);
            log.info("-> [SEEDER] user SUPER_ADMIN has been created {} / {}", superAdmin.getUsername(), "root");
        } else log.info("-> [SEEDER] The database has registered users");
    }
}
