package org.xiaoguo.iweb.volunteer.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 * @author marker
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:root.xml")
public class MongoTemplateTest {
	 @Autowired 
	 private MongoTemplate mongo;

	    //@Test
	    public void insert(){
	        Customer c = new Customer();
	        c.setFirstName("wu");
	        c.setLastName("wei");
	        mongo.insert(c); 
	        
	    }
	    
	    @Test
	    public void update(){
	    	List<Customer> findAll = mongo.findAll(Customer.class);
	    	findAll.forEach((c)->{
	    		c.setFirstName(System.currentTimeMillis()+"");
	    		mongo.save(c);
	    	});
	    }
	    @Test
	    public void select(){
	    	List<Customer> findAll = mongo.findAll(Customer.class);
	    	findAll.forEach((c)->System.out.println(c));
	    }
	  
}
