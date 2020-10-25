package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class MongoDb {
    private final String uri = "mongodb+srv://kafka-user:[비밀번호]@sensor-data-cluster.r5kof.mongodb.net/sensors?retryWrites=true&w=majority";
    private MongoCollection<Document> collection;

    public MongoDb() {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("sensors");
        collection = database.getCollection("kafka-data");
    }

    public void putData(String name, String temperature) {
        if (name.length() > 0 && temperature.length() > 0) {
            Document doc = new Document("name", name)
                    .append("temperature", temperature);
            collection.insertOne(doc);
        }
    }
}
