package cl.experti.admission.webapp.service.login;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.experti.admission.webapp.security.AdmissionAuthority;
import cl.experti.admission.webapp.security.AdmissionUser;
import cl.experti.admission.ws.login.LoginRequest;
import cl.experti.admission.ws.login.LoginResponse;
import cl.experti.admission.ws.login.UserLogin;
import cl.experti.admission.ws.login.UserLoginResponse;

@Service("loginService")
public class WebServiceUserLoginService implements UserLoginService {
    @Resource(name = "loginServiceClient")
    private cl.experti.admission.ws.login.LoginService loginService;

    @Override
    public UserDetails loginUser(String userName, String password) throws AuthenticationException {
	UserLogin userLoginParams = new UserLogin();
	LoginRequest userParams = new LoginRequest();
	userParams.setUsername(userName);
	userParams.setPassword(password);
	userLoginParams.setLoginRequest(userParams);
	UserLoginResponse userLoginResponse = loginService.userLogin(userLoginParams);
	LoginResponse loginResponse = userLoginResponse.getLoginResponse();
	BigDecimal loginResponseCode = loginResponse.getResponseCode();
	boolean authenticated = BigDecimal.ZERO.equals(loginResponseCode);

	if (authenticated) {
	    return new AdmissionUser(userName, password, true, true, true, true, mapRoles(loginResponse));
	} else {
	    AuthenticationException authEx = mapLoginException(loginResponse);
	    throw authEx;
	}
    }
    
    private Collection<GrantedAuthority> mapRoles(LoginResponse loginResponse) {
 	List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

 	for (String userRole : loginResponse.getUserRoles()) {
 	    roles.add(new AdmissionAuthority(userRole));
 	}

 	return roles;
     }
     
     private AuthenticationException mapLoginException(LoginResponse loginResponse) {
 	AuthenticationException authEx = null;
 	BigDecimal responseCode = loginResponse.getResponseCode();
 	switch (responseCode.intValue()) {
 	case 1:
 	    authEx = new UsernameNotFoundException("Usuario no encontrado");
 	    break;
 	case 2:
 	    authEx = new BadCredentialsException("Contrase\u00f1a no corresponde al usuario ingresado");
 	    break;
 	default:
 	    authEx = new InsufficientAuthenticationException("Fallo no contemplado");
 	    break;
 	}
 	
 	throw authEx;
     }
}