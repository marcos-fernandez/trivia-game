package net.marcos_fernandez.triviagame;

import lombok.extern.slf4j.Slf4j;
import net.marcos_fernandez.triviagame.model.User;
import net.marcos_fernandez.triviagame.repository.UsersRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static java.util.Collections.singleton;
import static net.marcos_fernandez.triviagame.model.User.ADMIN_AUTHORITY;

@Slf4j
@SpringBootApplication
public class TriviaGameApplication {

    private static final String ADMIN = "admin";

    public static void main(final String[] args) {
        SpringApplication.run(TriviaGameApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(final UsersRepository usersRepository, final PasswordEncoder passwordEncoder) {
        return args -> this.createDefaultAdminUserIfAbsent(usersRepository, passwordEncoder);
    }

    private void createDefaultAdminUserIfAbsent(final UsersRepository usersRepository, final PasswordEncoder passwordEncoder) {
        final Optional<User> admin = usersRepository.findByUsername(ADMIN);
        if (admin.isEmpty()) {
            log.warn(String.format("Admin user not found. Creating default %s user", ADMIN));
            final User defaultAdminUser = User.empty()
                    .toBuilder()
                    .username(ADMIN)
                    .name(ADMIN)
                    .password(passwordEncoder.encode(ADMIN))
                    .authorities(singleton(ADMIN_AUTHORITY))
                    .build();
            usersRepository.save(defaultAdminUser);
        }
    }

}
