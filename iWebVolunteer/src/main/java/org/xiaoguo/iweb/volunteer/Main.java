package org.xiaoguo.iweb.volunteer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	
		@SuppressWarnings("unused")
		private static ApplicationContext ctx;
		public static void main(String[] args) {
			initSpring();
		
		}
		
		private static void initSpring(){
			ctx = new ClassPathXmlApplicationContext("root.xml");
			logger.info("Spring is Load succ!");
		}
		
}
