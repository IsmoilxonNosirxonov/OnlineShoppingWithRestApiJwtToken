package uz.in.onlineshoppingwithrestapijwttoken;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.UserRole;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.User;
import uz.in.onlineshoppingwithrestapijwttoken.repository.UserRepository;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Online shopping", version = "1.0", description = "shopping app"))
public class OnlineShoppingWithRestApiJwtTokenApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingWithRestApiJwtTokenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            User user= User.builder()
                    .fullName("Admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("777"))
                    .phoneNumber("+998977777777")
                    .balance(1D)
                    .role(UserRole.ADMIN).build();
            userRepository.save(user);
        }
    }
}
