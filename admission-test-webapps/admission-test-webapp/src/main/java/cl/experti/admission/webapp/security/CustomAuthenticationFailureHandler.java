package cl.experti.admission.webapp.security;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("customAuthFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private static Logger logger;

	static {
		logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		logger.debug("Catching following auth exception: {}", exception);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
		response.sendRedirect(request.getContextPath() + "/login.jsf");
	}
}