package com.jorgefigueiredo.finance.data.parsers;

import com.jorgefigueiredo.finance.data.entities.TickerData;

public interface ITickerDataParser {

	TickerData parse(String input);
	
}
