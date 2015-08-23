package cl.experti.admission.webapp.beans.login;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

import cl.experti.admission.web.beans.BaseBean;

@Component
@Scope("request")
@SuppressWarnings("serial")
public class LoginBean extends BaseBean {
	private String username;
	private String password;
	private static Logger logger;

	static {
		logger = LoggerFactory.getLogger(LoginBean.class);
	}

	public void checkLoginErrors() {
		FacesContext facesContext = getFacesContext();
		if (!facesContext.isPostback()) {
			Exception authException = getSessionParameter(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (authException instanceof AuthenticationException) {
				this.getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
				addErrorMessage(null, authException.getMessage());
			}
		}
	}

	public String doLogin() throws Exception {
		logger.debug("Logging into the system");
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		FacesContext facesContext = getFacesContext();
		facesContext.responseComplete();
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}