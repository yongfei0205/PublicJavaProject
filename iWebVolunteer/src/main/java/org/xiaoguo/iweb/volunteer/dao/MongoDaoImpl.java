package org.xiaoguo.iweb.volunteer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.xiaoguo.iweb.volunteer.domain.Group;
import org.xiaoguo.iweb.volunteer.domain.User;
@Component
public class MongoDaoImpl  implements MongoDao{
	@Autowired 
	private MongoTemplate mongo;

	@Override
	public User addUser(User user){
		User u=getUser(user.getName());
		if(u!=null){
			return u;		
		}
		else{
			mongo.insert(user);
			return getUser(user.getName());
		}
	}
	@Override
	public User getUser(String name){
		Query query=new  Query();
		query.addCriteria(new Criteria("name").is(name));
		return mongo.findOne(query, User.class);
	}
	@Override
	public void update(User user){
		mongo.save(user);
	}
	@Override
	public boolean delUser(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getUserByGroup(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getUserByGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getUsersByPermission(int permission) {
		// TODO Auto-generated method stub
		return null;
	}
}
