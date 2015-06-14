package org.xiaoguo.iweb.volunteer.web.servlet;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.xiaoguo.iweb.volunteer.contants.GlobalContants;
import org.xiaoguo.iweb.volunteer.domain.User;

public class LoginServlet extends BaseHttpServlet {
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 4446921789955056805L;

	@Override
	protected JSONObject doService(HttpSession session, JSONObject param,
			JSONObject result) {

		Object login = session.getAttribute("login");
		if (session != null && login != null) {
			logger.debug(login);
			result.put("state", true);
		}
		String name = param.has("name") ? param.getString("name") : null;
		String password = param.has("password") ? param.getString("password")
				: null;
		if (name != null && password != null) {
			User user = oservice.getUserByName(name);
			if (user == null) {
				result.put(GlobalContants.key_state_code,
						GlobalContants.state_code_not_user);
			} else if (user.getPwd().equals(password)) {
				session.setAttribute("login", name);
				session.setAttribute("loginTime", System.currentTimeMillis());
				result.put(GlobalContants.key_state_code,
						GlobalContants.STATE_CODE_OK);
			} else {
				result.put(GlobalContants.key_state_code,
						GlobalContants.state_code_password_fail);
			}

		} else {
			result.put(GlobalContants.key_state_code,
					GlobalContants.state_code_not_user);
		}
		return result;
	}

}
