package com.miliworks.virgo.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.miliworks.virgo.config.ConfigManager;
import com.miliworks.virgo.db.DbContent;
import com.miliworks.virgo.db.DbManager;
import com.miliworks.virgo.db.DbManagerFactory;
import com.miliworks.virgo.db.MongoDbContent;

public class TestDB {

	@Before
	public void setUp() throws Exception {
		ConfigManager.getInstance().setConfPath(System.getProperty("user.dir")+"/target/test-classes/conf");
		ConfigManager.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMongo() {
		DbManager db = DbManagerFactory.createDbManager("mongodb");
		assertTrue(db.init("test", "test1"));
		
		assertTrue(db.deleteAll());
		
		//插入数据
		DbContent content = new MongoDbContent();
		content.set("id", "123");
		content.set("key1", "abc");
		assertTrue(db.insert(content));
		
		// 查询
		DbContent search = new MongoDbContent();
		search.set("id", "124"); // wrong key
		List<DbContent> result = db.query(search);
		assertTrue(result.size() == 0);
		
		search.set("id", "123"); // corrent key
		result = db.query(search);
		assertTrue(result.size()==1);
		assertTrue(result.get(0).get("key1").equals("abc"));
	}

}
