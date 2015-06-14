package org.xiaoguo.iweb.volunteer.test;

import java.util.List;

import net.sf.json.JSONObject;

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
	
	//@Test
	public void test(){
		mongo.findAll(User.class).forEach(u->{
			System.out.println(JSONObject.fromObject(u));
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
	
	@Test
	public void queryId(){
		Query q=new  Query();
		q.addCriteria(new Criteria("id").is("55782742e3662451e8306bc7"));
		 mongo.find(q, User.class).forEach(u->System.out.println(JSONObject.fromObject(u)));
		
	}

}
