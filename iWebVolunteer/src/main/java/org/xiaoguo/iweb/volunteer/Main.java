package org.xiaoguo.iweb.volunteer;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.xiaoguo.iweb.volunteer.interceptor.UserSecurityInterceptor;

//@Configuration    
//@ComponentScan("org.xiaoguo.iweb.volunteer") 
//@EnableAutoConfiguration  
//@EnableWebSocket
@SpringBootApplication
@ImportResource("classpath:root.xml")
public class Main extends WebMvcConfigurerAdapter {

	private static Logger logger = Logger.getLogger(Main.class);
	
		@SuppressWarnings("unused")
		private static ApplicationContext ctx;
		public static void main(String[] args) {
			initSpring();
		
		}
		 /**
	     * 配置拦截器
	     * @author lance
	     * @param registry
	     */
		@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	    	registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/user/**").excludePathPatterns("/user/login/**");
		}
		private static void initSpring(){
			//ctx = new ClassPathXmlApplicationContext("root.xml");
			SpringApplication.run(Main.class);  	       
			logger.info("Spring is Load succ!");
		}
		
}
