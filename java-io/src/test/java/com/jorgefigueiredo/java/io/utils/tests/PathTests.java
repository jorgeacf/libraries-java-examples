package com.jorgefigueiredo.java.io.utils.tests;

import static org.junit.Assert.*;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Test;

public class PathTests {

	@Test
	public void testFileSystem() {
		
		FileSystem fs = FileSystems.getDefault();
		
		Path path = fs.getPath("/home/jorgeacf", "mysql_install_log");
		
		assertEquals("/", fs.getSeparator());
		
		assertEquals("/home/jorgeacf/mysql_install_log", path.toFile().getAbsolutePath());
		
		
	}

}
