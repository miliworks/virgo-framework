package com.miliworks.virgo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.miliworks.virgo.cache.CacheManager;
import com.miliworks.virgo.config.ConfigManager;

public class TestCache {

	@Before
	public void setUp() throws Exception {
		ConfigManager.getInstance().setConfPath(System.getProperty("user.dir")+"/target/test-classes/conf");
		ConfigManager.getInstance().init();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		assertTrue(CacheManager.getInstance().set("test1", "a", "1"));
		assertTrue(CacheManager.getInstance().get("test1", "a").equals("1"));
		assertTrue(CacheManager.getInstance().set("test2", "a", "2"));
		assertTrue(CacheManager.getInstance().get("test2", "a").equals("2"));
		
		assertTrue(CacheManager.getInstance().get("test1", "a").equals("1"));
		
		assertTrue(CacheManager.getInstance().set("test3", "a", "3")==false);
		assertTrue(CacheManager.getInstance().get("test3", "a") == null);
	}

}
