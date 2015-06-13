package org.xiaoguo.iweb.volunteer.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xiaoguo.iweb.volunteer.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:root.xml")
	
public class UserSerivceTest {
	@Autowired
	private MongoTemplate mongo;
	
	@Test
	public void test(){
		mongo.findAll(User.class).forEach(c->{
			System.out.println(c);
		});;
	}
	
	@Test
	public void select(){
		Query q=new  Query();
		q.addCriteria(new Criteria("name").is("郭健"));
		q.addCriteria(new Criteria("pwd").is("xiaoguo822"));
		
		List<User> find = mongo.find(q, User.class);
		System.out.println("");
	}

}
