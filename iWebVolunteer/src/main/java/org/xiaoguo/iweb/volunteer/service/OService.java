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
import org.xiaoguo.iweb.volunteer.contants.GlobalContants;
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
		User user = dao.getUser("郭健");
		user.setPermission(9);
		dao.update(user);
		//dao.addUser(user);
	}

	public boolean login(String name, String password) {
		User user = dao.getUser(name, password);		
		return user == null ? false : true;
	}
	
	public User loginByTel(String tel,String password){
		User user = dao.getUserByTel(tel, password);		
		return user;
	}

	public User getUserByName(String name) {
		return dao.getUser(name);
	}
	
	public User getUserById(String id) {
		return dao.getUserById(id);
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
		User user = copyField(json, new User());
		dao.addUser(user);
	}

	public int update(JSONObject json) {
		String id = json.getString("id");
		User user = dao.getUserById(id);
		if (user == null) {
			return GlobalContants.state_code_not_user;
		}
		user = copyField(json, user);
		dao.update(user);
		return GlobalContants.STATE_CODE_OK;
	}

	private User copyField(JSONObject jo, User u) {
		Iterator<?> keys = jo.keys();
		while (keys.hasNext()) {
			String key = keys.next().toString();
			try {
				Object value = jo.get(key);
				Class<? extends Object> class1 = value.getClass();
				if (class1 == Integer.class) {
					class1 = int.class;
				} else if (class1 == Boolean.class) {
					class1 = boolean.class;
				}
				Method method = u.getClass().getMethod(
						"set" + key.substring(0, 1).toUpperCase()
								+ key.substring(1), class1);

				method.invoke(u, value);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return u;
	}

	
}
