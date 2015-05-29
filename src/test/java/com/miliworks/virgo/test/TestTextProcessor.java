package com.miliworks.virgo.test;

import com.miliworks.virgo.config.ConfigManager;
import com.miliworks.virgo.crawler.CrawlerManager;
import com.miliworks.virgo.crawler.CrawlerManagerObserver;
import com.miliworks.virgo.crawler.WebPage;
import com.miliworks.virgo.log.Log;
import com.miliworks.virgo.text.TextProcessor;
import com.miliworks.virgo.text.WordInfo;
import com.miliworks.virgo.text.WordIterator;

public class TestTextProcessor implements CrawlerManagerObserver {
	public static void main(String[] args) throws Exception {

		String text = "这是一个中文分词的例子，你可以直接运行它！IKAnalyer can analysis english text too";
		String text2 = "看空伯克希尔的基金经理道格-卡斯在巴菲特股东会上质疑公司规模过于庞大，巴菲特和芒格纷纷表示反对。巴菲特回答指，因为伯克希尔的规模问题，毫无疑问可能表现会逊于以往，但是其指，在市场不好时，规模可以成为优势，因为伯克希尔有能力提供流动性和稳定性。";

		TextProcessor tp = new TextProcessor();
		
		// 处理text1
		WordIterator iter = tp.analyze(text);

		while (iter.hasNext()) {
			WordInfo word = iter.next();

			System.out.println(word.getOffsetBegin() + "-" + word.getOffsetEnd() + ":" + word.getWord());
		}

		iter.end();
		
		// 处理text2
		WordIterator iter2 = tp.analyze(text2);

		while (iter2.hasNext()) {
			WordInfo word = iter2.next();

			System.out.println(word.getOffsetBegin() + "-" + word.getOffsetEnd() + ":" + word.getWord());
		}

		iter2.end();
	}

	public boolean shouldVisit(String url) {
		Log.d("TestCrawler", "shouldVisit:" + url);
		return true;
	}

	public void visit(WebPage page) {
		Log.d("TestCrawler", "visit:" + page.getHtml());

	}
}
