package org.xiaoguo.iweb.volunteer.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoguo.iweb.volunteer.dao.MongoDao;
import org.xiaoguo.iweb.volunteer.domain.User;

@Service
public class Serivce {

	@Autowired
	private MongoDao dao;

	@PostConstruct
	private void init(){
		User user=new User();
		user.setCo("掌趣科技");
		user.setName("郭健");
		user.setMan(true);
		user.setEmail("i@xiaoguo822.com");
		user.setJobPost("码农");
		user.setPwd("xiaoguo822");
		user.setQQ("83575126");
		user.setTel("15810669623");
		dao.addUser(user);
	}
}
