package org.xiaoguo.iweb.volunteer.web.servlet;

import javax.servlet.http.HttpSession;

import org.xiaoguo.iweb.volunteer.domain.User;

import net.sf.json.JSONObject;

public class MyInfoServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6345338407507042853L;

	@Override
	protected JSONObject doService(HttpSession session, JSONObject param,
			JSONObject result) {
		String name = session.getAttribute("login").toString();
		User user = oservice.getUserByName(name);
		result=JSONObject.fromObject(user);
		return result;
	}

	@Override
	protected boolean hasLogin() {
		return true;
	}
}
