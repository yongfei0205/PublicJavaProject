package org.xiaoguo.iweb.volunteer.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;

//@Configuration
public class MongoConfiguration {
 
    @Value("${mongodb.host}")
    private String mongoHost;
    @Value("${mongodb.port}")
    private String mongoPort;
    @Value("${mongodb.database}")
    private String dataBase; 
    @Value("${mongodb.username}")
    private String username; 
    @Value("${mongodb.password}")
    private String password;
 
    //@Bean(name="abs")
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://"+mongoHost+":"+mongoPort));
        UserCredentials userCredentials = new UserCredentials(username, password);
        //优先从secondary节点进行读取操作，secondary节点不可用时从主节点读取数据
        client.setReadPreference(ReadPreference.secondaryPreferred());
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(client,dataBase,userCredentials);
      
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
     
        MappingMongoConverter mongoConverter = (MappingMongoConverter) mongoTemplate.getConverter();
        //把spring data mongodb _class这个字段去掉
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mongoTemplate;
    }
}
