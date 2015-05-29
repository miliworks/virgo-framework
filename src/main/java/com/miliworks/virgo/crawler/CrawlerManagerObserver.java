package com.miliworks.virgo.crawler;

public interface CrawlerManagerObserver{

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	public boolean shouldVisit(String url);

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	public void visit(WebPage page);
}
