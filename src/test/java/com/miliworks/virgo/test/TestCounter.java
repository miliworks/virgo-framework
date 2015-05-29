package com.miliworks.virgo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.miliworks.virgo.config.ConfigManager;
import com.miliworks.virgo.counter.CounterManager;

public class TestCounter {

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
		assertTrue(CounterManager.getInstance().reset("article_num"));
		assertTrue(CounterManager.getInstance().increment("article_num")==1);
		assertTrue(CounterManager.getInstance().increment("article_num")==2);
		assertTrue(CounterManager.getInstance().reset("article_num"));
		assertTrue(CounterManager.getInstance().increment("article_num")==1);
		
		assertTrue(CounterManager.getInstance().reset("start_num"));
		assertTrue(CounterManager.getInstance().increment("start_num")==1);
		assertTrue(CounterManager.getInstance().increment("start_num")==2);
		assertTrue(CounterManager.getInstance().reset("start_num"));
		assertTrue(CounterManager.getInstance().increment("start_num")==1);
		
		assertTrue(CounterManager.getInstance().increment("article_num")==2);
		assertTrue(CounterManager.getInstance().increment("start_num")==2);
	}

}
