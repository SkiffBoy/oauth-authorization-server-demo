package com.baeldung.web;

import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

	@GetMapping({"/", "/index"})
	public String index() {
		var html = "<ul>" +
						"<li><a target=\"_blank\" href=\"/articles?grant_type=authorization_code\">访问资源服务的/articles(授权码方式认证)</a></li>" +
						"<li><a target=\"_blank\" href=\"/articles?grant_type=client_credentials\">访问资源服务的/articles(客户端方式认证)</a></li>" +
    				"</ul>";

		return html;
	}

	/**
	 * '/authorized' 是授权码模式注册的 'redirect_uri'
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/authorized", params = OAuth2ParameterNames.ERROR)
	public String authorizationFailed(HttpServletRequest request) {
		String errorCode = request.getParameter(OAuth2ParameterNames.ERROR);
		String errorText = errorCode;
		if (StringUtils.hasText(errorCode)) {
			errorText += "<br/>" + request.getParameter(OAuth2ParameterNames.ERROR_DESCRIPTION) + "<br/>" + request.getParameter(OAuth2ParameterNames.ERROR_URI);
		}

		return errorText;
	}

}
