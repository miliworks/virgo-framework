package com.miliworks.virgo.db;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.miliworks.virgo.config.ConfigManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoDbManager implements DbManager {
	private DBCollection collection;

	public boolean init(String dbName, String tableName) {
		try {
			String host = ConfigManager.getInstance().getString("virgo.db.mongo.host");
			int port = ConfigManager.getInstance().getInt("virgo.db.mongo.port");

			Mongo mg = new Mongo(host, port);

			DB db = mg.getDB(dbName);

			collection = db.getCollection(tableName);

			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<DbContent> query(DbContent match)
	{
		List<DbContent> list = new ArrayList<DbContent>();

		if (collection != null) {
			// 构建search对象
			DBObject matchObj = new BasicDBObject();

			for (String key : match.keySet()) {
				matchObj.put(key, match.get(key));
			}
			
			DBCursor cur = collection.find(matchObj);

			while (cur.hasNext()) {
				DBObject obj = cur.next();
				MongoDbContent content = new MongoDbContent();
				content.initFromDbObject(obj);

				list.add(content);
			}
		}

		return list;
	}
	
	public List<DbContent> queryAll() {
		List<DbContent> list = new ArrayList<DbContent>();

		if (collection != null) {
			DBCursor cur = collection.find();

			while (cur.hasNext()) {
				DBObject obj = cur.next();
				MongoDbContent content = new MongoDbContent();
				content.initFromDbObject(obj);

				list.add(content);
			}
		}

		return list;
	}

	public boolean insert(DbContent content) {
		if (collection != null && content != null) {
			DBObject obj = new BasicDBObject();

			for (String key : content.keySet()) {
				obj.put(key, content.get(key));
			}

			collection.save(obj);

			return true;
		}

		return false;
	}

	public boolean update(DbContent content) {
		return insert(content);
	}

	public boolean delete(String id) {
		if (collection != null) {
			DBObject obj = new BasicDBObject();
			obj.put("_id", id);
			collection.remove(obj);
		}

		return false;
	}

	public boolean delete(DbContent content) {
		if (content != null)
			return delete((String) content.get("_id"));
		else
			return false;
	}

	public boolean deleteAll() {
		if (collection != null) {
			collection.drop();
			return true;
		}
		return false;
	}
}
