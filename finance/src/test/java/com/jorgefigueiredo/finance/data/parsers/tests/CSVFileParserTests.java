package com.jorgefigueiredo.finance.data.parsers.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.jorgefigueiredo.finance.data.entities.InstrumentData;
import com.jorgefigueiredo.finance.data.parsers.CSVFileParser;
import com.jorgefigueiredo.finance.data.parsers.ICSVFileParser;

public class CSVFileParserTests {

	@Test
	public void test() throws IOException {
		
		String filePath = "/home/jorgeacf/dev/github/libraries-java-examples/input-files/^gdaxi_2015_06_19.csv";
		
		File file = new File(filePath);
		
		ICSVFileParser parser = new CSVFileParser();
		
		InstrumentData data = parser.parse(file);
		
		assertEquals(1, data.getData().length);
		
	}

}
