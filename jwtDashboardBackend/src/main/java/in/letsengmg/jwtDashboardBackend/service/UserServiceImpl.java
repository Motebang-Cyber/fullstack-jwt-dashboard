package in.letsengmg.jwtDashboardBackend.service;

import in.letsengmg.jwtDashboardBackend.entity.Role;
import in.letsengmg.jwtDashboardBackend.entity.User;
import in.letsengmg.jwtDashboardBackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public User register(String email, String password) {
        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .role(Role.USER)
                .build();

        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return user;
    }
}
