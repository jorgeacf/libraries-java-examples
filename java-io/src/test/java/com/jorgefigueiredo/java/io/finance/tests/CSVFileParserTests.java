package com.jorgefigueiredo.java.io.finance.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.jorgefigueiredo.java.io.finance.CSVFileParser;
import com.jorgefigueiredo.java.io.finance.ICSVFileParser;
import com.jorgefigueiredo.java.io.finance.InstrumentData;

public class CSVFileParserTests {

	@Test
	public void test() {
		
		String filePath = "/home/jorgeacf/dev/github/libraries-java-examples/input-files/^gdaxi_2015_06_19.csv";
		
		File file = new File(filePath);
		
		ICSVFileParser parser = new CSVFileParser();
		
		InstrumentData data = parser.parse(file);
		
		assertEquals(1, data.getData().length);
		
	}

}
