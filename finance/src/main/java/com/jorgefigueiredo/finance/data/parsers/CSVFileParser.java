package com.jorgefigueiredo.finance.data.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.jorgefigueiredo.finance.data.entities.InstrumentData;
import com.jorgefigueiredo.finance.data.entities.TickerData;

public class CSVFileParser implements ICSVFileParser {

	public InstrumentData parse(File input) throws IOException {
		
		HashMap<String, String> map = new HashMap<String, String>();
		List<TickerData> data = new LinkedList<TickerData>();
		
		ITickerDataParser tickerDataParser = new TickerDataParser();
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(input);
			br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null ) {
				
				if(line.contains(":") && line.split(":").length == 2) {
					String[] lineSegments = line.split(":");
					map.put(lineSegments[0], lineSegments[1]);
				}
				else {
					data.add(tickerDataParser.parse(line));
				}
			}
			
		} catch (IOException e) {
			return null;
		}
		finally {
			if(br != null) {
				br.close();
			}
		}
		
		/*
		InstrumentData instrumentData = new InstrumentData
				(URI.create(map.get("uri")),
				map.get("ticker"),
				
						
						);
		*/
		
		return null;
	}
	
}
