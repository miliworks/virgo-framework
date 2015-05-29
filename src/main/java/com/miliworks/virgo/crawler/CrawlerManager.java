package com.miliworks.virgo.crawler;

import java.util.HashMap;
import java.util.Map;

import com.miliworks.virgo.config.ConfigManager;
import com.miliworks.virgo.log.Log;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerManager {
	private static CrawlerManager _instance;

	private CrawlController crawlerController;
	private int numberOfCrawlers;
	private CrawlerThread crawlerThread;
	private CrawlerManagerObserver crawlerObserver;

	public static CrawlerManager getInstance() {
		if (_instance == null)
			_instance = new CrawlerManager();

		return _instance;
	}

	private CrawlerManager() {
	}

	public void init(CrawlerManagerObserver observer) {

		Log.d(Consts.TAG, "crawler initializing...");
		String crawlStorageFolder = ConfigManager.getInstance().getString("virgo.crawler.tmp", "crawler/root");
		numberOfCrawlers = ConfigManager.getInstance().getInt("virgo.crawler.num", 3);
		String useragentName = ConfigManager.getInstance().getString("virgo.crawler.useragent", "crawler4j");
		boolean enableRobotstxt = ConfigManager.getInstance().getBoolean("virgo.crawler.robotstxtenable", false);

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setUserAgentName(useragentName);
		// 如果支持robotstxt，crawler4j代码需要做一些调整，修复nullpointexception
		robotstxtConfig.setEnabled(enableRobotstxt);

		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

		try {
			crawlerController = new CrawlController(config, pageFetcher, robotstxtServer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setCrawlerObserver(observer);
	}
	

	/*
	 * For each crawl, you need to add some seed urls. These are the first URLs
	 * that are fetched and then the crawler starts following links which are
	 * found in these pages
	 */
	public void addSeed(String url) {
		Log.d(Consts.TAG, "addSeed:" + url);

		if (crawlerController != null)
			crawlerController.addSeed(url);
	}

	/*
	 * Start the crawl.
	 */
	public void start() {
		Log.d(Consts.TAG, "start crawler manager");
		
		if (crawlerThread != null)
			stop();

		crawlerThread = new CrawlerThread();
		crawlerThread.start();
	}

	/*
	 * Stop the crawl.
	 */
	public void stop() {
		if (crawlerThread != null) {

		}
	}

	public CrawlerManagerObserver getCrawlerObserver() {
		return crawlerObserver;
	}

	public void setCrawlerObserver(CrawlerManagerObserver crawlerObserver) {
		this.crawlerObserver = crawlerObserver;
	}

	class CrawlerThread extends Thread {
		@Override
		public void run() {
			super.run();

			if (crawlerController != null)
				crawlerController.start(VirgoCrawler.class, numberOfCrawlers);
		}
	}
}
