package com.miliworks.virgo.crawler;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class VirgoCrawler extends WebCrawler {
	
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpeg" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz|ico))$");

	
	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		// 首先看url是否在排除列表中，如果不在，再由observer负责继续检查
		if( !FILTERS.matcher(url.getPath()).matches() )
		{
			if( CrawlerManager.getInstance().getCrawlerObserver() != null )
				return CrawlerManager.getInstance().getCrawlerObserver().shouldVisit(url.getURL());
		}
		
		return false;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {

		WebPage p = new WebPage();
		p.setUrl(page.getWebURL().getURL());
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			p.setHtml(htmlParseData.getHtml());
		}
		
		if( CrawlerManager.getInstance().getCrawlerObserver() != null )
			CrawlerManager.getInstance().getCrawlerObserver().visit(p);
	}
}
