package cn.com.starest.nextoa.dashboard.support.spring.security.mvc;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import cn.com.starest.nextoa.dashboard.support.auth.service.AuthEventService;
import cn.com.starest.nextoa.dashboard.support.spring.security.AuthEventHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dz
 */
public class AuthenticationSuccessHandlerMvcImpl extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Log logger = LogFactory.getLog(AuthenticationSuccessHandlerMvcImpl.class);

	@Autowired
	private AuthEventService userAuditService;

	public AuthenticationSuccessHandlerMvcImpl() {
		super();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		AuthEvent userLogin = AuthEventHelper.buildSucceedAuthEvent(request);
		userAuditService.saveUserAuthEvent(userLogin);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
