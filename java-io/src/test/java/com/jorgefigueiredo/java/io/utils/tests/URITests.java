package com.jorgefigueiredo.java.io.utils.tests;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import static org.junit.Assert.*;

public class URITests {

	@Test
	public void testURIWebResource() throws URISyntaxException {
		
		
		URI uri = new URI("http://www.jorgefigueiredo.com/posts/post1?id=123&name=jorge");
		
		assertEquals("http", uri.getScheme());
		
		assertEquals("www.jorgefigueiredo.com", uri.getHost());
		
		assertEquals("/posts/post1", uri.getPath());
		
		assertEquals("id=123&name=jorge", uri.getQuery());
		
	}
	
	@Test
	public void testURILocalFileResource() throws URISyntaxException {
		
		URI uri = new URI("file:///opt/jdk1.8.0");
		
		assertEquals("file", uri.getScheme());
		
		assertEquals(null, uri.getHost());
		
		assertEquals("/opt/jdk1.8.0", uri.getPath());
		
	}
	
}
