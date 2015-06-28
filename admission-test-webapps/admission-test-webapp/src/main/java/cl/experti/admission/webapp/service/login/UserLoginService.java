package cl.experti.admission.webapp.service.login;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserLoginService {
    public UserDetails loginUser(String userName, String password) throws AuthenticationException;
}