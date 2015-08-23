package cl.experti.admission.ws.login.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponse {
	@XmlElement(name = "responseCode", required = true, nillable = false)
	private BigDecimal loginResponseCode;
	@XmlElement(name = "responseMessage")
	private String loginResponseMessage;
	@XmlElement(name = "userRoles")
	private List<String> userRoles;

	public BigDecimal getLoginResponseCode() {
		return loginResponseCode;
	}

	public void setLoginResponseCode(BigDecimal loginResponseCode) {
		this.loginResponseCode = loginResponseCode;
	}

	public String getLoginResponseMessage() {
		return loginResponseMessage;
	}

	public void setLoginResponseMessage(String loginResponseMessage) {
		this.loginResponseMessage = loginResponseMessage;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}
}