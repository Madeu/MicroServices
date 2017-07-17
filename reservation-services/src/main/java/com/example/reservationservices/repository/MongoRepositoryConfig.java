package com.example.reservationservices.repository;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoRepositoryConfig extends AbstractMongoConfiguration{

	@Value("${db.host.port}")
	private int port;

	@Value("${db.host.name}")
	private String host;

//	@Value("${db.name}")
//	private String dbname;

	@Override
	public Mongo mongo() throws Exception {
		ServerAddress serverAdress = new ServerAddress(host,port);
		Mongo mongo = new MongoClient(serverAdress);
		mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongo;
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

	@Override
	protected String getDatabaseName() {
		return "local";
	}
}
