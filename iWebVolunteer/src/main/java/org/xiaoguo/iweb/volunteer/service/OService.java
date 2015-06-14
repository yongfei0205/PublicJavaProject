package org.xiaoguo.iweb.volunteer.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoguo.iweb.volunteer.dao.MongoDao;
import org.xiaoguo.iweb.volunteer.domain.Permission;
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

	public User getUserByName(String name) {
		return dao.getUser(name);
	}

	public List<User> getUserListByName(String name) {
		User user = getUserByName(name);
		List<User> collect = dao.getAllUser().stream().filter(u -> {
			if (user.getPermission() < Permission.head.getId()) {
				if (user.getGroup() == u.getGroup()) {
					return true;
				} else {
					if (u.getPermission() >= Permission.head.getId()) {
						return true;
					}
				}
			} else {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
		addUser(JSONObject.fromObject(collect.get(0)));
		return collect;
	}

	public void addUser(JSONObject json) {
		User user = (User) JSONObject.toBean(json, User.class);
		User usr2 = copyField(json, new User());
		System.out.println("");
	}

	private User copyField(JSONObject jo, User u) {
		Iterator keys = jo.keys();
		while (keys.hasNext()) {
			String key = keys.next().toString();
			try {
				Object value=jo.get(key);
				Method method = u.getClass().getMethod(
						"set" + key.substring(0, 1).toUpperCase()
								+ key.substring(1),value.getClass());
				method.invoke(u, value);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}
}
