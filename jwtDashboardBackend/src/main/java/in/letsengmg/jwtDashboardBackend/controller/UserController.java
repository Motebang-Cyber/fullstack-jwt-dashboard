package in.letsengmg.jwtDashboardBackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class UserController {
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userDashboard() {
        return "User Dashboard Data";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Admin Secret Data";
    }
}
