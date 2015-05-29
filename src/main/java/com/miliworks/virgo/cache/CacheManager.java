package com.miliworks.virgo.cache;

import java.util.HashMap;
import java.util.Map;

import com.miliworks.virgo.config.ConfigManager;

import redis.clients.jedis.Jedis;

public class CacheManager {
	private static CacheManager _instance;
	
	private Map<String, CacheClient> mapCache;

	public static CacheManager getInstance() {
		if (_instance == null)
			_instance = new CacheManager();

		return _instance;
	}

	// virgo.cache.<tag>.xxx定义了一个cache服务点，通过tag指定具体的cache服务点
	private CacheManager() {
		mapCache = new HashMap<String,CacheClient>();
	}

	public boolean set(String tag, String key, String value) {
		CacheClient client = getClient(tag);
		if( client.isEnable() )
		{
			client.getRedis().set(key,value);
			
			return true;
		}
		else
			return false;
	}
	
	public boolean set(String tag, String key, Long value) {
		CacheClient client = getClient(tag);
		if( client.isEnable() )
		{
			client.getRedis().set(key,String.valueOf(value));
			
			return true;
		}
		else
			return false;
	}

	public String get(String tag, String key) {
		CacheClient client = getClient(tag);
		if( client.isEnable() )
			return client.getRedis().get(key);
		
		return null;
	}
	
	public Long increment(String tag, String key) {
		CacheClient client = getClient(tag);
		if( client.isEnable() )
		{
			return client.getRedis().incr(key);
		}
		else
			return 0L;
	}

	private CacheClient getClient(String tag)
	{
		CacheClient client = mapCache.get(tag);
		if( client == null )
		{
			// 检查相应cache tag的配置项，并加载相应配置
			client = new CacheClient();
			boolean enable = ConfigManager.getInstance().getBoolean("virgo.cache."+tag+".enable",false);
			client.setEnable(enable);
			
			if( enable )
			{
				Jedis redis = new Jedis(ConfigManager.getInstance().getString("virgo.cache."+tag+".address"),
						ConfigManager.getInstance().getInt("virgo.cache."+tag+".port"));
				redis.connect();
				
				client.setRedis(redis);
			}
		}
		
		return client;
	}
}
