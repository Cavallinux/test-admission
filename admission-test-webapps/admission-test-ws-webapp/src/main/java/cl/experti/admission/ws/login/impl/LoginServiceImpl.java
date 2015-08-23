package cl.experti.admission.ws.login.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.experti.admission.ws.login.LoginService;
import cl.experti.admission.ws.login.dto.LoginRequest;
import cl.experti.admission.ws.login.dto.LoginResponse;
import cl.experti.admission.ws.services.CheckUserLoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name = "checkLoginService")
	private CheckUserLoginService checkLoginService;
	private static Logger logger;

	static {
		logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	}

	@Override
	public LoginResponse userLogin(LoginRequest loginRequest) {
		logger.debug("Realizing login with following data: {}", ReflectionToStringBuilder.reflectionToString(loginRequest, ToStringStyle.MULTI_LINE_STYLE));
		LoginResponse loginResponse = checkLoginService.checkLoginService(loginRequest);
		logger.debug("Returning following login response: {}", ReflectionToStringBuilder.reflectionToString(loginResponse, ToStringStyle.MULTI_LINE_STYLE));
		return loginResponse;
	}
}