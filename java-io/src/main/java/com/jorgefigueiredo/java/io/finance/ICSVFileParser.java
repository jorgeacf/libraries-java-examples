package com.jorgefigueiredo.java.io.finance;

import java.io.File;

public interface ICSVFileParser {

	InstrumentData parse(File input);
	
}
