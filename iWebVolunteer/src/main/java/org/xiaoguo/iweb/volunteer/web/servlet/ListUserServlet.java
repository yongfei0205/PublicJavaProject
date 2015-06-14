package org.xiaoguo.iweb.volunteer.web.servlet;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.xiaoguo.iweb.volunteer.contants.GlobalContants;


public class ListUserServlet extends BaseHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3752321890550025502L;

	@Override
	protected JSONObject doService(HttpSession session, JSONObject param,
			JSONObject result) {
		String name = session.getAttribute("login").toString();
		JSONArray ja = new JSONArray();		
		oservice.getUserListByName(name).forEach(u -> {
			JSONObject jo =  JSONObject.fromObject(u);
			jo.remove("pwd");
			ja.add(jo);
		});
		result.put(GlobalContants.key_state_code,
				GlobalContants.STATE_CODE_OK);
		result.put("users", ja);
		return result;
	}
	
	@Override
	protected boolean hasLogin() {
		return true;
	}

}
