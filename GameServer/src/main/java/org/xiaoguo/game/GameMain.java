package org.xiaoguo.game;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xiaoguo.game.net.NetServer;



public class GameMain {
	private static Logger logger = Logger.getLogger(GameMain.class);

	@SuppressWarnings("unused")
	private static ApplicationContext ctx;
	public static void main(String[] args) {
		initSpring();
		NetServer.startNetService(8888);
	}
	
	private static void initSpring(){
		ctx = new ClassPathXmlApplicationContext("root.xml");
		logger.info("Spring is Load succ!");
	}
	

}
