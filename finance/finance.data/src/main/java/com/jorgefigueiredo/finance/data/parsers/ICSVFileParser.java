package com.jorgefigueiredo.finance.data.parsers;

import java.io.File;
import java.io.IOException;

import com.jorgefigueiredo.finance.data.entities.InstrumentData;

public interface ICSVFileParser {

	InstrumentData parse(File input) throws IOException;
	
}
