package org.xiaoguo.iweb.volunteer.web;

import org.springframework.stereotype.Service;

@Service
public class WebServer {
	/**
	private static Logger logger = Logger.getLogger(WebServer.class);
	private Server server;

	@PostConstruct
	private void init() {
		server = new Server(8880);
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/app");
		context.addServlet(LoginServlet.class, "/login");
		context.addServlet(ListUserServlet.class, "/userList");
		context.addServlet(MyInfoServlet.class, "/myinfo");
		server.setHandler(context);
		WebAppContext web = new WebAppContext("webRoot", "/");
		web.setWelcomeFiles(new String[] { "index.html" });
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { context, web });
		server.setHandler(handlers);
		// context.addVirtualHosts(new String[]{"iweb.xiaoguo822.com"});
		// context.addFilter(filterClass, pathSpec, dispatches)
		// server.setHandler(handler);
		// ResourceHandler resource_handler = new ResourceHandler();
		// resource_handler.setDirectoriesListed(true);
		// resource_handler.setWelcomeFiles(new String[] { "index.html" });
		// resource_handler.setResourceBase("webRoot/");
		// HandlerList handlers = new HandlerList();
		// handlers.setHandlers(new Handler[] { handler,resource_handler,
		// new DefaultHandler() });
		// server.setHandler(handlers);

		try {
			server.start();
		} catch (Exception e) {
			logger.error(e);
		}

		logger.info("WebServer is Started");
	}
*/
}
