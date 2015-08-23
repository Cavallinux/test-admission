package cl.experti.admission.webapp.security;

import org.springframework.security.core.AuthenticationException;

public class SoapFailedAuthenticationException extends AuthenticationException {

	public SoapFailedAuthenticationException(String msg) {
		super(msg);
	}

	public SoapFailedAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
