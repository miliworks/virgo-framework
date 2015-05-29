package com.miliworks.virgo.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mongodb.DBObject;

public class MongoDbContent implements DbContent {
	private Map<String,Object> map;
	
	public MongoDbContent()
	{
		map = new HashMap<String,Object>();
	}
	
	public boolean initFromDbObject(DBObject obj)
	{
		for( String key : obj.keySet() )
		{
			set( key, obj.get(key) );
		}
		
		return true;
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}

	public Object get(String key) {
		if( map != null )
			return map.get(key);
		
		return null;
	}

	public void set(String key, Object obj) {
		if( map != null )
			map.put(key, obj);
	}
}
