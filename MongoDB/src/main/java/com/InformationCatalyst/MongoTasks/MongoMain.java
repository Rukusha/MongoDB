package com.InformationCatalyst.MongoTasks;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


//Write a class that access a mongoDB via an appropriate java driver -so this will be a library reference via pom. 
//(Mongo access details will be provided). Via an approbate java class, interrogate the given mongo collection for the total number of entries,
//add a new entry and confirm the count goes up by one, delete the new entry and confirm the deletion. Note, 
//your class MUST cope with the possibility that the MongoDB is inaccessible, so should log the problem and exit gracefully. 

public class MongoMain {
	private static final Logger logger = LoggerFactory.getLogger(MongoMain.class);
	
	public static void main(String[] args) {
//		This tries to set up a connection to the MongoDB
		try {
			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://192.168.10.18:27017"));
			MongoDatabase exampleDB = mongoClient.getDatabase("example");
			MongoCollection<Document> collection = exampleDB.getCollection("collection");
//			This logs the amount of entries that can be found in the Database at the start
			logger.info("Total number of entries \n" + "The amount of entries = " + collection.countDocuments());

//			This section adds a entry to the Database
			Document insertDoc = new Document("Pokemon", "Eevee")
			        .append("Quantity", 1);
			collection.insertOne(insertDoc);

//			Logs the amount of entries after the new Doc has been added
			logger.info("Total number of entries \n" + "The amount of entries = " + collection.countDocuments());

//			This section will remove an entry from the database
			collection.deleteOne(insertDoc);
//			This logs the amount of entries that can be found in the Database at the start

			logger.info("Total number of entries \n" + "The amount of entries = " + collection.countDocuments());

		}catch(MongoException connectionDB){
			logger.info("The Database can not be reached");
	        logger.info(connectionDB.getMessage());

		}
	}

}
