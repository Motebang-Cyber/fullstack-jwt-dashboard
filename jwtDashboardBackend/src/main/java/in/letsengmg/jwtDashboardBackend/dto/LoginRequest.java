package in.letsengmg.jwtDashboardBackend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String Password;
}
