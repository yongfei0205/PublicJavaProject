package org.xiaoguo.iweb.volunteer.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 * 
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
	public void insert() {
		for (int i = 0; i < 10; i++) {
			Customer c = new Customer();
			c.setFirstName("wu");
			c.setLastName("__" + (10 - i));
			c.setAge(i * i);
			mongo.insert(c);
		}

	}

	// @Test
	public void update() {
		List<Customer> findAll = mongo.findAll(Customer.class);
		findAll.forEach((c) -> {
			c.setFirstName(System.currentTimeMillis() + "");
			mongo.save(c);
		});
	}

	@Test
	public void select() {
		Query q = new Query();
		// Criteria age = new Criteria("age");
		// q.addCriteria(age);
		
		q = q.with(
				new Sort(new Order[]{ new Order(Direction.DESC,"age"),new Order("lastName")}))
				.limit(10);
		List<Customer> findAll = mongo.find(q, Customer.class);
		findAll.forEach((c) -> System.out.println(c));
	}

	@Test
	public void select1() {
		Query q = new Query();
		Criteria age = new Criteria("age");
		age = new Criteria("age").lt(90).gt(70);
		age = age.orOperator(new Criteria("age").lt(50).gt(30));
		q.addCriteria(age);
		q = q.limit(1);
		List<Customer> findAll = mongo.find(q, Customer.class);
		findAll.forEach((c) -> System.out.println(c));
	}

	// @Test
	public void remove() {

		mongo.remove(new Query(), Customer.class);
	}

}
