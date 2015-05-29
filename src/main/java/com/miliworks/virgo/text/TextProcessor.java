package com.miliworks.virgo.text;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TextProcessor {
	private TextProcessorObserver wordObserver;
	Analyzer analyzer = null;
	
	public TextProcessor() {
		wordObserver = null;
		
		//构建IK分词器，使用smart分词模式
		analyzer = new IKAnalyzer(true);
	}
	
	public TextProcessor(TextProcessorObserver observer) {
		wordObserver = observer;
	}
	
	public WordIterator analyze(String text) {
		return analyze(new StringReader(text));
	}
	
	public WordIterator analyze(Reader textReader) {
		
		
		//获取Lucene的TokenStream对象
	    TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("myfield", textReader);
			//获取词元位置属性
		    OffsetAttribute  offset = ts.addAttribute(OffsetAttribute.class); 
		    //获取词元文本属性
		    CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
		    //获取词元文本属性
		    //TypeAttribute type = ts.addAttribute(TypeAttribute.class);

		    //重置TokenStream（重置StringReader）
		    ts.reset(); 
		    
		    return new WordIterator(ts,offset,term);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
	    }
		return null;
	}
	
}
