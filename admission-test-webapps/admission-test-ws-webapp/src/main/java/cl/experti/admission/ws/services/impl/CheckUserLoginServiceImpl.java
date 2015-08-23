package cl.experti.admission.ws.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.experti.admission.ws.login.dto.LoginRequest;
import cl.experti.admission.ws.login.dto.LoginResponse;
import cl.experti.admission.ws.services.CheckUserLoginService;

@Service("checkLoginService")
public class CheckUserLoginServiceImpl implements CheckUserLoginService {
	@Resource(name = "userNames")
	private Properties users;
	private static Logger logger;

	static {
		logger = LoggerFactory.getLogger(CheckUserLoginServiceImpl.class);
	}

	@Override
	public LoginResponse checkLoginService(LoginRequest request) {
		LoginResponse loginResponse = new LoginResponse();

		boolean existsUser = users.containsKey(request.getUserName());
		logger.debug("Exists user? {}", existsUser);

		if (existsUser) {
			boolean passwordMatches = StringUtils.equals(request.getPassword(), (String) users.get(request.getUserName()));
			logger.debug("Password matches? {}", passwordMatches);

			if (passwordMatches) {
				loginResponse.setLoginResponseCode(new BigDecimal(0));
				loginResponse.setLoginResponseMessage("Logged successfully");
				List<String> userRoles = new ArrayList<>();
				userRoles.add("AdmissionWebapp");
				loginResponse.setUserRoles(userRoles);
			} else {
				loginResponse.setLoginResponseCode(new BigDecimal(2));
				loginResponse.setLoginResponseMessage("Password do not matches");
			}
		} else {
			loginResponse.setLoginResponseCode(new BigDecimal(1));
			loginResponse.setLoginResponseMessage("User not found");
		}

		return loginResponse;
	}
}