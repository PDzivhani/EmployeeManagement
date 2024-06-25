package com.teamc.ems.config;

import com.teamc.ems.repository.UserRepo;
import com.teamc.ems.user.Role;
import com.teamc.ems.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AdminInitializer implements CommandLineRunner {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByEmail("admin@example.com").isEmpty()) {
            User admin = User.builder()
                    .firstname("Default")
                    .lastname("Admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("adminpassword"))
                    .role(Role.ADMIN)
                    .build();
            userRepo.save(admin);
        }
    }
}
