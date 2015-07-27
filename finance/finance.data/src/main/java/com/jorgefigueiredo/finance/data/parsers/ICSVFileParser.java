package com.jorgefigueiredo.finance.data.parsers;

import java.io.File;

import com.jorgefigueiredo.finance.data.entities.InstrumentData;

public interface ICSVFileParser {

	InstrumentData parse(File input);
	
}
