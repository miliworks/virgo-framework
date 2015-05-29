package com.miliworks.virgo.text;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

public class WordIterator {
	private TokenStream ts;
	private OffsetAttribute offsetAttribute;
	private CharTermAttribute charAttribute;

	public WordIterator(TokenStream ts,OffsetAttribute offsetAttribute,CharTermAttribute charAttribute) {
		this.ts = ts;
		this.offsetAttribute = offsetAttribute;
		this.charAttribute = charAttribute;
	}
	
	public boolean hasNext()
	{
		try {
			return ts.incrementToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public WordInfo next()
	{
		WordInfo word = new WordInfo();
		word.setWord(charAttribute.toString());
		word.setOffsetBegin(offsetAttribute.startOffset());
		word.setOffsetEnd(offsetAttribute.endOffset());
		
		return word;
	}
	
	public void end()
	{
		//关闭TokenStream（关闭StringReader）
		try {
			ts.end();
			ts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   // Perform end-of-stream operations, e.g. set the final offset.
	}
}
