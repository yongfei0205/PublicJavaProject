package org.xiaoguo.iweb.volunteer.web.servlet;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.xiaoguo.iweb.volunteer.web.json.ResultJsonObject;

public class LoginServlet extends BaseHttpServlet {
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 4446921789955056805L;

	@Override
	protected JSONObject doService(HttpSession session, JSONObject param) {
		JSONObject result = new ResultJsonObject();
		Object login = session.getAttribute("login");
		if (session != null && login != null) {
			logger.debug(login);
			result.put("state", true);
		}
		String name = param.has("name") ? param.getString("name") : null;
		String password = param.has("password") ? param.getString("password")
				: null;
		if (name != null && password != null) {
			if (oservice.login(name, password)) {
				session.setAttribute("login", name);
				session.setAttribute("loginTime", System.currentTimeMillis());
				result.put("state", true);
			} else {
				result.put("msg", "用户不存在/密码错误!");
			}

		} else {
			result.put("msg", "输入用户名和密码!");
		}
		return result;
	}

}
