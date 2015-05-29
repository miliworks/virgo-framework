package com.miliworks.virgo.test;

import com.miliworks.virgo.config.ConfigManager;
import com.miliworks.virgo.crawler.CrawlerManager;
import com.miliworks.virgo.crawler.CrawlerManagerObserver;
import com.miliworks.virgo.crawler.WebPage;
import com.miliworks.virgo.log.Log;

public class TestCrawler implements CrawlerManagerObserver {
	public static void main(String[] args) throws Exception {
		
		ConfigManager.getInstance().setConfPath(System.getProperty("user.dir")+"/target/test-classes/conf");
		ConfigManager.getInstance().init();
		
		TestCrawler crawler = new TestCrawler();
		
		CrawlerManager.getInstance().init(crawler);
		
		CrawlerManager.getInstance().addSeed("http://news.hexun.com/");
		
		CrawlerManager.getInstance().start();
	}

	public boolean shouldVisit(String url) {
		Log.d("TestCrawler", "shouldVisit:"+url);
		return true;
	}

	public void visit(WebPage page) {
		Log.d("TestCrawler", "visit:"+page.getHtml());
		
	}
}
