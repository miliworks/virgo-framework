package com.miliworks.virgo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.miliworks.virgo.config.ConfigManager;

public class TestConfig {

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
		assertTrue(ConfigManager.getInstance().getString("virgo.test.teststring").equals("ok"));
		assertTrue(ConfigManager.getInstance().getBoolean("virgo.test.testboolean")==true);
		assertTrue(ConfigManager.getInstance().getBoolean("virgo.test.testboolean2")==false);
		assertTrue(ConfigManager.getInstance().getInt("virgo.test.testint")==10);
		assertTrue(ConfigManager.getInstance().getInt("virgo.test.testint2")==-1);
	}

}
