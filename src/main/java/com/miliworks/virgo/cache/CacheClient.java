package com.miliworks.virgo.cache;

import redis.clients.jedis.Jedis;

public class CacheClient {
	private boolean enable = false;
	private Jedis redis;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Jedis getRedis() {
		return redis;
	}

	public void setRedis(Jedis redis) {
		this.redis = redis;
	}
}
