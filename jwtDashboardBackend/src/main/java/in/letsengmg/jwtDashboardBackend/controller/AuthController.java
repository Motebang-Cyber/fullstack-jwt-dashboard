package in.letsengmg.jwtDashboardBackend.controller;

import in.letsengmg.jwtDashboardBackend.config.JwtService;
import in.letsengmg.jwtDashboardBackend.dto.LoginRequest;
import in.letsengmg.jwtDashboardBackend.dto.LoginResponse;
import in.letsengmg.jwtDashboardBackend.dto.SignupRequest;
import in.letsengmg.jwtDashboardBackend.entity.User;
import in.letsengmg.jwtDashboardBackend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest req) {
        return service.register(req.getEmail(), req.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {

        User user = service.login(req.getEmail(), req.getPassword());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token);
    }

}
