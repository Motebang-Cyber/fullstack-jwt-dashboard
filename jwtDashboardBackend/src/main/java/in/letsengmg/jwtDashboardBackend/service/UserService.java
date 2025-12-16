package in.letsengmg.jwtDashboardBackend.service;

import in.letsengmg.jwtDashboardBackend.entity.User;

public interface UserService {
    User register(String email, String password);
    User login(String email, String password);
}
