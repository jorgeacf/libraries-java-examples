package java_io.streams.demos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;




public class CopyFileUtils {

	
	public static void CopyByteByByte(String from, String to) throws IOException {
		
		
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
		
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			
			int c;
			
			while((c = in.read()) != -1 ) {
				out.write(c);
			}
			
			
		}
		finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			
		}
		
		
	}
	
	public static void CopyCharacteres(String from, String to) throws IOException {
		
		FileReader inputStream = null;
		FileWriter outputStream = null;
		
		try {
			
			inputStream = new FileReader(from);
			outputStream = new FileWriter(to);
			
			int c;
			
			while ((c = inputStream.read()) != -1) {
				outputStream.write(c);
			}
			
		}
		finally {
			
			if(inputStream != null) {
				inputStream.close();
			}
			
			if(outputStream != null) {
				outputStream.close();
			}
			
		}
		
	}
	
	public static void CopyLines(String from, String to) throws IOException {
		
		
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		
		try {
			
			inputStream = new BufferedReader(new FileReader(from));
			outputStream = new PrintWriter(new FileWriter(to));
			
			String l;
			while((l = inputStream.readLine()) != null) {
				outputStream.println(l);
			}
			
		}
		finally {
			
			if(inputStream != null) {
				inputStream.close();
			}
			
			if(outputStream != null) {
				outputStream.close();
			}
		}
		
	}
	
	
}
