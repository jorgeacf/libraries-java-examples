package com.jorgefigueiredo.java.io.utils.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileIOTests {

	private static String PATH = ".";
	private static String FILE = "file";
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		
		FileSystem fs = FileSystems.getDefault();
		
		Path path = fs.getPath(PATH, FILE);
		
		File file = path.toFile();
		
		if(file.exists()) {
			
			file.delete();
			
		}
		
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testFileWrite() throws IOException {
		
		
		OutputStream os = new FileOutputStream(new File(FILE));
		
		String s = "Jorge Figueiredo";
		
		byte[] b = s.getBytes("UTF-8");
		
		os.write(b);
		
		InputStream is = new FileInputStream(new File(FILE));
		
		String x = "";
		
		int c = 0;
		while ((c = is.read()) != -1) {
			
			x += (char)c;
			
		}
		
		assertEquals("Jorge Figueiredo", x);
		
	}

	@Test
	public void testCreateFile() throws IOException {
		
		FileSystem fs = FileSystems.getDefault();
		
		Path path = fs.getPath(PATH, FILE);
		
		File file = path.toFile();
		
		assertTrue(file.createNewFile());
		
		assertTrue(file.exists());
		
	}
	
}
