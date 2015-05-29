package com.miliworks.virgo.db;

public class DbManagerFactory {
	public static DbManager createDbManager(String type) {
		if( type.equals("mongodb") )
		{
			MongoDbManager manager = new MongoDbManager();
			return manager;
		}
		else
			return null;
	}
}
