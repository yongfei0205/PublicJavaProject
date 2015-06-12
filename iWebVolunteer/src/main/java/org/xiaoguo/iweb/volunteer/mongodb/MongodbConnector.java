package org.xiaoguo.iweb.volunteer.mongodb;

import com.mongodb.MongoClient;

public class MongodbConnector {
	private static MongodbConnector instance = new MongodbConnector();

	private MongoClient mongo;
	public static MongodbConnector getInstance() {
		return instance;
	}
	
	public void init(){	
		mongo=new MongoClient("www.xiaoguo822.com", 10001);
	}
	
	public MongoClient getMongoClient(){
		return mongo;
	}
}
