package com.miliworks.virgo.db;

import java.util.List;

public interface DbManager {
	public boolean init(String dbName, String tableName);

	public List<DbContent> query(DbContent match);
	
	public List<DbContent> queryAll();

	public boolean insert(DbContent content);

	public boolean update(DbContent content);

	public boolean delete(String id);

	public boolean delete(DbContent content);

	public boolean deleteAll();
}
