package cl.experti.admission.web.beans;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class BaseBean implements java.io.Serializable {
    protected String getRequestParameter(String name) {
        Map<String, String> requestParameterMap = getExternalContext().getRequestParameterMap();
        return requestParameterMap.get(name);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSessionParameter(String name) {
        HttpSession httpSession = getHttpSession();
        return (T) httpSession.getAttribute(name);
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T getFlashParameter(String name) {
        Flash flash = getExternalContext().getFlash();
        return (T) flash.get(name);
    }

    protected <T> void setFlashParameter(String name, T value) {
        getExternalContext().getFlash().put(name, value);
    }

    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected void addInfoMessage(String messageFor, String message) {
        getFacesContext().addMessage(messageFor, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    protected void addErrorMessage(String messageFor, String message) {
        getFacesContext().addMessage(messageFor, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    protected HttpSession getHttpSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(true);
    }
    
	@SuppressWarnings("unchecked")
	protected <T extends ServletRequest> T getRequest() {
		ExternalContext context = getExternalContext();
		return (T) context.getRequest();
	}

	@SuppressWarnings("unchecked")
	protected <T extends ServletResponse> T getResponse() {
		ExternalContext context = getExternalContext();
		return (T) context.getResponse();
	}
}