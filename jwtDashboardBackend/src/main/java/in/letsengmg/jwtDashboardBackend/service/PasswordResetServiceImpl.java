package in.letsengmg.jwtDashboardBackend.service;

import in.letsengmg.jwtDashboardBackend.entity.User;
import in.letsengmg.jwtDashboardBackend.repository.UserRepository;
import in.letsengmg.jwtDashboardBackend.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService{
    private final UserRepository repo;
    private final EmailUtil emailUtil;
    private final PasswordEncoder encoder;

    @Override
    public void sendResetLink(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        repo.save(user);

        emailUtil.send(
                email,
                "Reset Password",
                "Click link to reset: http://localhost:5173/reset/" + token
        );
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = repo.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setPassword(encoder.encode(newPassword));
        user.setResetToken(null);
        repo.save(user);
    }
}
