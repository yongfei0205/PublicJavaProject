package org.xiaoguo.iweb.volunteer.web.servlet;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xiaoguo.iweb.volunteer.web.json.ResultJsonObject;

public class ListUserServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3752321890550025502L;

	@Override
	protected JSONObject doService(HttpSession session, JSONObject param) {
		String name = session.getAttribute("login").toString();
		JSONObject json = new ResultJsonObject();
		json.put("state", true);
		JSONArray ja = new JSONArray();
		oservice.getUserListByName(name).forEach(u -> {
			JSONObject jo = new JSONObject(u);
			ja.put(jo);
		});
		json.put("users", ja);
		return json;
	}

}
