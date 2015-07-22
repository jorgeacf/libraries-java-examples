package com.jorgefigueiredo.java.io.finance.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jorgefigueiredo.java.io.finance.ITickerDataParser;
import com.jorgefigueiredo.java.io.finance.TickerData;
import com.jorgefigueiredo.java.io.finance.TickerDataParser;

public class TickerDataParserTests {

	@Test
	public void parseValidLine() {
		
		String input = "1434697379,11122.4004,11123.9102,11121.6396,11121.6602,0";
		
		ITickerDataParser parser = new TickerDataParser();
		
		TickerData tickerData = parser.parse(input);
		
		assertEquals("1434697379", tickerData.getTimeStamp());
		assertEquals(Double.parseDouble("11122.4004"), (double)tickerData.getClose(), 4);
		assertEquals(Double.parseDouble("11123.9102"), (double)tickerData.getHigh(), 4);
		assertEquals(Double.parseDouble("11121.6396"), (double)tickerData.getLow(), 4);
		assertEquals(Double.parseDouble("11121.6602"), (double)tickerData.getOpen(), 4);
		assertEquals(Integer.parseInt("0"), (int)tickerData.getVolume());
		
	}

}
