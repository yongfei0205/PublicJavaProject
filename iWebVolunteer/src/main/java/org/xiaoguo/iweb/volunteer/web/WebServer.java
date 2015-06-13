package org.xiaoguo.iweb.volunteer.web;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.stereotype.Service;
import org.xiaoguo.iweb.volunteer.web.servlet.ListUserServlet;
import org.xiaoguo.iweb.volunteer.web.servlet.LoginServlet;

@Service
public class WebServer {
	private static Logger logger = Logger.getLogger(WebServer.class);
	private Server server;

	private WebAppContext context;

	@PostConstruct
	private void init() {
		server = new Server(8880);
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(LoginServlet.class, "/login");
		context.addServlet(ListUserServlet.class, "/userList");
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

}
