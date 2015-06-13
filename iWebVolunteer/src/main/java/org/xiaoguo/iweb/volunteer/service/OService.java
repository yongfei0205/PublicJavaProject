package org.xiaoguo.iweb.volunteer.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoguo.iweb.volunteer.dao.MongoDao;
import org.xiaoguo.iweb.volunteer.domain.User;


@Service
public class OService {

	private static OService instance;

	public static OService getInstance() {
		return instance;
	}

	@Autowired
	private MongoDao dao;

	@PostConstruct
	private void init() {
		instance = this;
		User user = new User();
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

	public boolean login(String name, String password) {
		User user = dao.getUser(name, password);
		return user == null ? false : true;
	}
	
	public User getUserByName(String name){
		return dao.getUser(name);
	}
	
	public List<User> getUserListByName(String name){
		User user=getUserByName(name);
		return dao.getAllUser();
	}
}
