package com.jorgefigueiredo.finance.data.parsers;

import com.jorgefigueiredo.finance.data.entities.TickerData;

public class TickerDataParser implements ITickerDataParser {

	private final String delimiter = ",";
	
	public TickerData parse(String input) {
		
		String[] inputSegments = input.split(delimiter);
		
		TickerData tickerData = null;
		
		try {
			String timestamp = inputSegments[0];
			Double close = Double.parseDouble(inputSegments[1]);
			Double high = Double.parseDouble(inputSegments[2]);
			Double low = Double.parseDouble(inputSegments[3]);
			Double open = Double.parseDouble(inputSegments[4]);
			Integer volume = Integer.parseInt(inputSegments[5]);
		
			tickerData = new TickerData(
					timestamp,
					close,
					high,
					low,
					open,
					volume);
			
		}
		catch(Exception ex) {
			
			// Add log
			//throw ex;
		}
		
		return tickerData;
	}

}
