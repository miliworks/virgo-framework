package com.miliworks.virgo.counter;

import com.miliworks.virgo.cache.CacheClient;
import com.miliworks.virgo.cache.CacheManager;

public class CounterManager {
	private static CounterManager _instance;

	public static CounterManager getInstance() {
		if (_instance == null)
			_instance = new CounterManager();

		return _instance;
	}
	
	public Long increment(String key)
	{
		return CacheManager.getInstance().increment(Consts.COUNTER_CACHE_TAG, key);
	}
	
	public boolean reset(String key)
	{
		return CacheManager.getInstance().set(Consts.COUNTER_CACHE_TAG, key,0L);
	}
}
