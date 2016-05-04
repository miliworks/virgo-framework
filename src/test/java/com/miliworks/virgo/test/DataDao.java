package com.miliworks.virgo.test;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;

public class DataDao {
	private static DataDao _instance = null;
	private DBCollection contents = null;

	private DataDao() {
		reconnectDB();
	}

	public static DataDao getInstance() {
		if (_instance == null)
			_instance = new DataDao();

		return _instance;
	}

	private boolean reconnectDB() {
		Mongo mg;
		try {
			mg = new Mongo("localhost", 10001);

			// Database
			for (String name : mg.getDatabaseNames()) {
				System.out.println("mongodb dbName: " + name);
			}

			DB db = mg.getDB("data");
			//
			for (String name : db.getCollectionNames()) {
				System.out.println("mongodb collectionName: " + name);
			}

			contents = db.getCollection("contents");

			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<Content> queryAll() {
		//
		DBCursor cur = contents.find();
		while (cur.hasNext()) {
			System.out.println(cur.next());
		}

		return null;
	}

	public boolean insert(Content content) {
		DBObject obj = new BasicDBObject();
		obj.put("url", content.getUrl());
		obj.put("text", content.getText());
		obj.put("url_links", content.getLinksUrl());
		if (contents != null) {
			contents.save(obj);
			return true;
		}

		return false;
	}
}
