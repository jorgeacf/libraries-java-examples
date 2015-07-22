package com.jorgefigueiredo.java.io.finance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CSVFileParser implements ICSVFileParser {

	@Override
	public InstrumentData parse(File input) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		List<TickerData> data = new LinkedList<TickerData>();
		
		ITickerDataParser tickerDataParser = new TickerDataParser();
		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			
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
		
		InstrumentData instrumentData = new InstrumentData
				(URI.create(map.get("uri")),
				map.get("ticker"),
				
						
						);
		
		
		return null;
	}
	
}
