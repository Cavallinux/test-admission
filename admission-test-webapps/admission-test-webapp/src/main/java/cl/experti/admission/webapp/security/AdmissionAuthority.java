package cl.experti.admission.webapp.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

public class AdmissionAuthority implements GrantedAuthority {
	private String authority;

	public AdmissionAuthority() {
		this(StringUtils.EMPTY);
	}

	public AdmissionAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
