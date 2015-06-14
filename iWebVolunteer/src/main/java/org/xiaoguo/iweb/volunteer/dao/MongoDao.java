package org.xiaoguo.iweb.volunteer.dao;

import java.util.List;

import org.xiaoguo.iweb.volunteer.domain.Group;
import org.xiaoguo.iweb.volunteer.domain.User;

public interface MongoDao {

	public User addUser(User user);

	public User getUser(String name);

	public User getUserById(String id);

	public User getUser(String name, String password);

	public void update(User user);

	public boolean delUser(String name);

	public List<User> getAllUser();

	public List<User> getUserByGroup(int index);

	public List<User> getUserByGroup(Group group);

	public List<User> getUsersByPermission(int permission);

}
