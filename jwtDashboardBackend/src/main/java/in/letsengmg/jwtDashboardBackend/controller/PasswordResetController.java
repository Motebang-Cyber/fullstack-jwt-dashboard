package in.letsengmg.jwtDashboardBackend.controller;

import in.letsengmg.jwtDashboardBackend.dto.EmailRequest;
import in.letsengmg.jwtDashboardBackend.dto.ResetPasswordRequest;
import in.letsengmg.jwtDashboardBackend.service.PasswordResetService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService service;

    @PostMapping("/forgot")
    public String forgot(@RequestBody EmailRequest req) {
        service.sendResetLink(req.getEmail());
        return "Email sent";
    }

    @PostMapping("/reset")
    public String reset(@RequestBody ResetPasswordRequest req) {
        service.resetPassword(req.getToken(), req.getNewPassword());
        return "Password updated";
    }
}
