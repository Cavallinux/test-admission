package cl.experti.admission.webapp.security;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cl.experti.admission.webapp.service.login.UserLoginService;

@Service("admissionLoginProvider")
public class AdmissionLoginProvider extends AbstractUserDetailsAuthenticationProvider {
    @Resource(name = "loginService")
    private UserLoginService loginService;
    private static Logger logger;
    
    static {
	logger = LoggerFactory.getLogger(AdmissionLoginProvider.class);
    }
    
    @PostConstruct
    public void init() {
	setHideUserNotFoundExceptions(false);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
	logger.debug("TO-DO Method");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	String password = authentication.getCredentials().toString();
	UserDetails userDetails = loginService.loginUser(username, password);
	return userDetails;
    }
}