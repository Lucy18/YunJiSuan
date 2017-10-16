package com.nju.YunJiSuan;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongodbTest {

	public void test() {
		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		// 与数据最大连接数50
		build.connectionsPerHost(50);
		// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
		build.threadsAllowedToBlockForConnectionMultiplier(50);
		build.connectTimeout(1 * 60 * 1000);
		build.maxWaitTime(2 * 60 * 1000);
		MongoClientOptions options = build.build();
		MongoClient client = new MongoClient("localhost", options);

		// 获取数据库test,不存在的话，会自动建立该数据库
		MongoDatabase db = client.getDatabase("test");
		// 获取data集合，不存在的话，会自动建立该集合（相当于关系数据库中的数据表）
		MongoCollection<Document> users = db.getCollection("person");
		Document document = new Document();
		document.append("name", "lilei");
		document.append("age", 18);
		users.insertOne(document);
		// MongoClient使用完后必须要close释放资源
		client.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongodbTest mongodbTest=new MongodbTest();
		mongodbTest.test();
	}

}
