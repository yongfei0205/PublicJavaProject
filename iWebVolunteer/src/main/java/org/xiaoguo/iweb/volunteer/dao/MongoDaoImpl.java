package org.xiaoguo.iweb.volunteer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.xiaoguo.iweb.volunteer.domain.Group;
import org.xiaoguo.iweb.volunteer.domain.User;

import com.mongodb.WriteResult;

@Component
public class MongoDaoImpl implements MongoDao {
	@Autowired
	private MongoTemplate mongo;

	@Override
	public User addUser(User user) {
		User u = getUser(user.getName());
		if (u != null) {
			return u;
		} else {
			mongo.insert(user);
			return getUser(user.getName());
		}
	}

	@Override
	public User getUser(String name) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		return mongo.findOne(query, User.class);
	}

	@Override
	public void update(User user) {
		mongo.save(user);
	}

	@Override
	public boolean delUser(String name) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		WriteResult remove = mongo.remove(query, User.class);
		return remove.isUpdateOfExisting();
	}

	@Override
	public List<User> getAllUser() {
		return mongo.findAll(User.class);
	}

	@Override
	public List<User> getUserByGroup(int group) {
		Query query = new Query();
		query.addCriteria(new Criteria("group").is(group));
		return mongo.find(query, User.class);
	}

	@Override
	public List<User> getUserByGroup(Group group) {
		return getUserByGroup(group.getIndex());
	}

	@Override
	public List<User> getUsersByPermission(int permission) {
		Query query = new Query();
		query.addCriteria(new Criteria("permission").gte(permission));
		return mongo.find(query, User.class);
	}

	@Override
	public User getUser(String name, String password) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		query.addCriteria(new Criteria("pwd").is(password));
		return mongo.findOne(query, User.class);
	}
}
