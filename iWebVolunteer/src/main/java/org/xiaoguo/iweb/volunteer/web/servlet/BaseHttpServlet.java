package org.xiaoguo.iweb.volunteer.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.xiaoguo.iweb.volunteer.contants.GlobalContants;
import org.xiaoguo.iweb.volunteer.service.OService;

public abstract class BaseHttpServlet extends HttpServlet {

	protected OService oservice = OService.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3022392638692006721L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getInputStream();
		Enumeration<String> parameterNames = req.getParameterNames();
		JSONObject param = new JSONObject();
		if (req.getMethod().toLowerCase().equals("get")) {
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = req.getParameter(name);
				param.put(name, value);
			}
		} else {
			// param=new JSONObject(req.getInputStream().re);
		}
		JSONObject result = new JSONObject();
		;
		if (hasLogin() && !isLogin(req.getSession())) {

			result.put(GlobalContants.key_state_code,
					GlobalContants.STATE_CODE_not_login);
			;
		} else {
			result = doService(req.getSession(), param, result);
		}
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(result.toString());
		out.flush();
		out.close();
	}

	protected abstract JSONObject doService(HttpSession session,
			JSONObject param, JSONObject result);

	/**
	 * 需要登录
	 * 
	 * @return
	 */
	protected boolean hasLogin() {
		return false;
	}

	protected boolean isLogin(HttpSession session) {
		return (session != null && session.getAttribute("login") != null);
	}
}
