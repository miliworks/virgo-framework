package com.miliworks.virgo.db;

import java.util.Set;

public interface DbContent {
	public Set<String> keySet();

	public Object get(String key);

	public void set(String key, Object obj);
}
