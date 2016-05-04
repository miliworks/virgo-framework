package com.miliworks.virgo.test;

import com.miliworks.virgo.crawler.CrawlerManagerObserver;
import com.miliworks.virgo.crawler.WebPage;
import com.miliworks.virgo.log.Log;
import com.miliworks.virgo.text.TextProcessor;
import com.miliworks.virgo.text.WordInfo;
import com.miliworks.virgo.text.WordIterator;

public class TextDivider implements CrawlerManagerObserver {
    public static void main(String[] argvs) throws Exception {

        if (argvs == null || argvs.length < 1) {
            System.out.println("usage: java -jar TextDivider.jar \"<text>\"");
            return;
        }

        String text = argvs[0];

        TextProcessor tp = new TextProcessor();

        // 处理text1
        WordIterator iter = tp.analyze(text);

        StringBuilder sb = new StringBuilder();
        boolean isFistWord = true;
        while (iter.hasNext()) {
            WordInfo word = iter.next();

            if (isFistWord) {
                isFistWord = false;
            } else {
                sb.append(",");
            }
            sb.append(word.getWord());
        }

        System.out.println("result:" + sb.toString());

        iter.end();
    }

    public boolean shouldVisit(String url) {
        Log.d("TestCrawler", "shouldVisit:" + url);
        return true;
    }

    public void visit(WebPage page) {
        Log.d("TestCrawler", "visit:" + page.getHtml());

    }
}
