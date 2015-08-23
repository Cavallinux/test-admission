package cl.experti.admission.ws.login.config;

import java.util.Set;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.wsdl.service.factory.DefaultServiceConfiguration;

public class LoginServiceRequiredFieldProcessor extends DefaultServiceConfiguration {
	@Resource(name = "loginServiceRequiredFields")
	private Set<String> requiredFieldsOccurrences;

	@Override
	public Long getWrapperPartMinOccurs(MessagePartInfo mpi) {
		QName name = mpi.getName();
		String localName = name.getLocalPart();
		if (isInOcurrences(localName)) {
			return 1L;
		} else {
			return super.getWrapperPartMinOccurs(mpi);
		}
	}

	private boolean isInOcurrences(String localName) {
		return requiredFieldsOccurrences.contains(localName);
	}

	public Set<String> getRequiredFieldsOccurrences() {
		return requiredFieldsOccurrences;
	}

	public void setRequiredFieldsOccurrences(Set<String> requiredFieldsOccurrences) {
		this.requiredFieldsOccurrences = requiredFieldsOccurrences;
	}
}