package in.letsengmg.jwtDashboardBackend.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
}
